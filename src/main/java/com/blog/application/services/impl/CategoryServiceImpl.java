package com.blog.application.services.impl;

import com.blog.application.entities.Category;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.CategoryDto;
import com.blog.application.repositories.CategoryRepo;
import com.blog.application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category categorySave = this.categoryRepo.save(category);
        return this.modelMapper.map(categorySave,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category save = this.categoryRepo.save(category);
        return this.modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> allCategory = this.categoryRepo.findAll();
        List<CategoryDto> allCategoryDto = allCategory.stream().map((category)-> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return allCategoryDto;
    }

    @Override
    public CategoryDto getSingleCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }

//    public Category dtoToCategory(CategoryDto categoryDto){
//        Category category = this.modelMapper.map(categoryDto,Category.class);
//        return category;
//    }
//
//    public CategoryDto categoryToDto(Category category){
//        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
//        return categoryDto;
//    }

}
