package com.samy.blog.myblog.services;


import com.samy.blog.myblog.components.JwtTokenUtil;
import com.samy.blog.myblog.domains.LoginResponse;
import com.samy.blog.myblog.domains.User;
import com.samy.blog.myblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    public User createUser(User user) throws Exception {
        if (user == null) {
            throw new Exception("Data expected with this request");
        }
        if (user.getEmail() == null || user.getEmail().length() == 0) {
            throw new Exception("Email field is Empty");
        }
        if (user.getPassword() == null || user.getPassword().length() == 0) {
            throw new Exception("Please provide a password");
        }
        if (user.getName() == null || user.getName().length() == 0) {
            throw new Exception("Name field is empty");
        }
        if (!validateEmail(user.getEmail())) {
            throw new Exception("Email is invalid");
        }
        User user1 = getByEmail(user.getEmail());
        if (user1 != null) {
            throw new Exception("Email is already registered");
        }
        user.setRegisteredDate(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(user);
    }

    public boolean validateEmail(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //Login Service
    public LoginResponse loginUser(User user) throws Exception {
        User user1 = getByEmail(user.getEmail());


        if(user1 == null){
            throw new Exception("User not found");
        }

        if(!user1.getPassword().equals(user.getPassword())){
            throw  new Exception("Password does not match");
        }

        //generate token
        final String token = jwtTokenUtil.generateToken(user1);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user1.getId());
        loginResponse.setName(user1.getName());
        loginResponse.setToken(token);
        loginResponse.setEmail(user.getEmail());

        return loginResponse;


    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      return getByEmail(email);

    }


}
