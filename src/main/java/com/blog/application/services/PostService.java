package com.blog.application.services;

import com.blog.application.payloads.PostDto;
import com.blog.application.payloads.PostResponse;
import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update post
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post by User
    List<PostDto> getAllPostsByUser(Integer userId);

    //get All post by category
    List<PostDto> getAllPostsByCategory(Integer categoryId);

    //get search post
    List<PostDto> searchPosts(String keyword);



}
