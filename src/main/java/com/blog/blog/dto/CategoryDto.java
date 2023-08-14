package com.blog.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {

    private Integer categoryId;
    private String categoryTitle;
    private String categoryDescription;
    
}
