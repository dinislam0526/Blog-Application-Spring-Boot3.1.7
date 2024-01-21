package com.blog.application.services;

import com.blog.application.entities.Post;
import com.blog.application.payloads.PostDto;

import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update post
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get all post
    List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post by User
    List<PostDto> getAllPostsByUser(Integer userId);

    //get All post by category
    List<PostDto> getAllPostsByCategory(Integer categoryId);

    //get search post
    List<Post> searchPosts(String keyword);



}
