package com.example.gradle.gradledemo.services;


import com.example.gradle.gradledemo.data.User;
import com.example.gradle.gradledemo.data.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Sel(String username) {
        return userMapper.Sel(username);
    }

    public Set<User> all() {
        return userMapper.all();
    }
}