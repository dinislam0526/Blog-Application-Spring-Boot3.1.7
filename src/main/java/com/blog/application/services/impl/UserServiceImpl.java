package com.blog.application.services.impl;

import com.blog.application.entities.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.UserDto;
import com.blog.application.repositories.UserRepo;
import com.blog.application.security.UserDetail;
import com.blog.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        //only one new line
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = this.dtoToUser(userDto);
        User userSave = this.userRepo.save(user);
        return this.userToDto(userSave);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = this.userRepo.save(user);
        UserDto userDt1 = this.userToDto(updateUser);
        return userDt1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);

//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }

    public UserDto userToDto(User user){
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName((user.getName()));
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepo.findByName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

}
