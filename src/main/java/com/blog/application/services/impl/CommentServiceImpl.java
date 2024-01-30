package com.blog.application.services.impl;

import com.blog.application.entities.Comment;
import com.blog.application.entities.Post;
import com.blog.application.entities.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.CommentDto;
import com.blog.application.repositories.CommentRepo;
import com.blog.application.repositories.PostRepo;
import com.blog.application.repositories.UserRepo;
import com.blog.application.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment saveComment = this.commentRepo.save(comment);

        return this.modelMapper.map(saveComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        this.commentRepo.delete(comment);
    }

}
