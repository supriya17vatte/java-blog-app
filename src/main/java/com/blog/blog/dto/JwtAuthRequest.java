package com.blog.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthRequest {
    private String userName;
        private String password;

    
}
