package com.samy.blog.myblog.services;


import com.samy.blog.myblog.domains.User;
import com.samy.blog.myblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user){
        return userRepository.save(user);
    }


}
