package com.blog.application.services;

import com.blog.application.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
     CategoryDto createCategory(CategoryDto categoryDto);

     //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //getAll
    List<CategoryDto> getAllCategory();

    //getSingleCategory
    CategoryDto getSingleCategory(Integer categoryId);


}
