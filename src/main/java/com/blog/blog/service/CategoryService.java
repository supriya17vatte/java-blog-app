package com.blog.blog.service;

import java.util.List;

import com.blog.blog.dto.CategoryDto;
import com.blog.blog.entities.Category;
import com.blog.blog.exceptions.CategoryNotFoundException;

public interface CategoryService {

    //create
    CategoryDto createCategory(Category category);

    //get All
    List<Category> getAllCategories();

    //get
    Category getCategoryById(int id) throws CategoryNotFoundException;

    //update
    CategoryDto updateCategory(Category category);

    //delete
    public String deleteCategory(int id);
    
}
