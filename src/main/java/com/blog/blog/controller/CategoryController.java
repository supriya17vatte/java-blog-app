package com.blog.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.dto.CategoryDto;
import com.blog.blog.entities.Category;
import com.blog.blog.exceptions.CategoryNotFoundException;
import com.blog.blog.service.CategoryService;

@RestController
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody Category category){
      CategoryDto categoryDto =  categoryService.createCategory(category);
        return new ResponseEntity<>(categoryDto,HttpStatus.CREATED);

    }
    //Update
    @PutMapping("/category/update")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody Category category){
        CategoryDto updatedCategory = categoryService.updateCategory(category);
        return new ResponseEntity<>(updatedCategory,HttpStatus.ACCEPTED);

    }

    //GET 
    @GetMapping("/category/{id}")
    public Category findById(@PathVariable int id)throws CategoryNotFoundException{
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/category")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();

    }
    //Delete
    @DeleteMapping("/category/{id}")
    public String deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }
    
}
