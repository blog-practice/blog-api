package com.samy.blog.myblog.controllers;

import com.samy.blog.myblog.domains.LoginResponse;
import com.samy.blog.myblog.domains.User;
import com.samy.blog.myblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws Exception {
       return userService.createUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody User user) throws Exception{
        return userService.loginUser(user);
    }
}
