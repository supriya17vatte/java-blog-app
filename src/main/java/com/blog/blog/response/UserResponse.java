package com.blog.blog.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    
}
