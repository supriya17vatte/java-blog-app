package com.blog.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.dto.JwtAuthRequest;
import com.blog.blog.dto.JwtAuthResponse;
import com.blog.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/auth") //auth/login
public class AuthController {
    

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{

        this.authenticate(request.getUserName(),request.getPassword());
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUserName());
        String token =this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse  response =  new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String userName, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(userName, password);
 
        try{
        this.authenticationManager.authenticate(authenticationToken);
        }
        catch(BadCredentialsException bd){
            System.out.println("Invalid Details !!");
            throw new Exception("Invalid Username and Password");

        }
        
    }
}
