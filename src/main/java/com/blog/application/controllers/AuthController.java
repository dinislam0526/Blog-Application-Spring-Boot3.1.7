package com.blog.application.controllers;

import com.blog.application.exceptions.ApiException;
import com.blog.application.payloads.AuthRequest;
import com.blog.application.payloads.AuthResponse;
import com.blog.application.payloads.UserDto;
import com.blog.application.security.JwtService;
import com.blog.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto newUserDto = this.userService.registerUser(userDto);
        return new ResponseEntity<>(newUserDto,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest authRequest) throws Exception {
        this.authentication(authRequest.getUsername(),authRequest.getPassword());
        String token = this.jwtService.generateToken(authRequest.getUsername());
        return new ResponseEntity<>(new AuthResponse(token,true), HttpStatus.OK);
    }

    private void authentication(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        }catch(Exception e){
            System.out.println("Exception started"+e);
            throw new ApiException("UserName or password invalid !!");
        }

    }

}
