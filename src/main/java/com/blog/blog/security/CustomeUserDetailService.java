package com.blog.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blog.entities.User;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.repository.UserRepository;

@Service
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from DB by username
        User user =this.userRepository.findByEmail(username).orElseThrow(() ->new ResourceNotFoundException("User", "email"+username,0 ));
        
        return user;
    }
    
}
