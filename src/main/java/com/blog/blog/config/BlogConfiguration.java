package com.blog.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogConfiguration {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    
}
