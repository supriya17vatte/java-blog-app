package com.blog.blog.serviceImpl;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.CategoryDto;
import com.blog.blog.entities.Category;
import com.blog.blog.exceptions.CategoryNotFoundException;
import com.blog.blog.repository.CategoryRepository;
import com.blog.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public ModelMapper mapper;

    @Override
    public CategoryDto createCategory(Category category) {
        Category savedCategory =  categoryRepository.save(category);
        CategoryDto categoryDto =  mapper.map(savedCategory, CategoryDto.class);
        return categoryDto;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();

    }

    @Override
    public Category getCategoryById(int id) throws CategoryNotFoundException {

       Category category = categoryRepository.findById(id).orElse(null);
       if(category!= null){
            return category;
       }
       else{
        throw new CategoryNotFoundException("Category not found with Id:"+id);
       }  
    }
    
    @Override
    public CategoryDto updateCategory(Category category) {
        Category existingCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);

        existingCategory.setCategoryTitle(category.getCategoryTitle());
        existingCategory.setCategoryDescription(category.getCategoryDescription());
       
        categoryRepository.save(existingCategory);
        CategoryDto categoryDto = mapper.map(existingCategory, CategoryDto.class);
        return categoryDto;

    }

    @Override
    public String deleteCategory(int id) {
              categoryRepository.deleteById(id);
              return "Category deleted :"+id;
    }   
}
