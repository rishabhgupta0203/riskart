package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.helper.LoginCredentials;
import com.example.service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
	UserService userservice;
    
    @PostMapping("/signup")
    void add(@RequestBody User user)
    {
        this.userservice.signup(user);
    }
    
    @PostMapping("/login")
    List<User> login(@RequestBody LoginCredentials lcr)
    {
    	List<User>user;
    	
    	user=this.userservice.findelementbyemail(lcr.getEmail());
        	System.out.println(user);

        	return user;
    	
    	
    	
    		
    }
//    
//    @PostMapping("/logout")
//    void logout()
//    {
//    	
//    }
    
    @GetMapping("/getprofile/{id}")
    User getprofile(@PathVariable("id") int id)
    {
    	List<User>user=this.userservice.getElementById(id);
    	return user.get(0);
    }
    
    
    @PostMapping("/updateProfile")
    void updateprofile(@RequestBody User user)
    {
    	userservice.updateElementById(user);
    	
    }
	
}
