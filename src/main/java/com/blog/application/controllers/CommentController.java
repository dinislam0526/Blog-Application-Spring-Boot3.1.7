package com.blog.application.controllers;

import com.blog.application.payloads.ApiResponse;
import com.blog.application.payloads.CommentDto;
import com.blog.application.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable Integer postId,
                                                    @PathVariable Integer userId){
        CommentDto commentDtos = this.commentService.createComment(commentDto, postId,userId);
        return new ResponseEntity<>(commentDtos, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is deleted successfully",true),HttpStatus.OK);
    }

}
