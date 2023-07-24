package com.blog.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto { 
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    
    
}
