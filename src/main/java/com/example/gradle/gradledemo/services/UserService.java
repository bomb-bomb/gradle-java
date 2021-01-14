package com.example.gradle.gradledemo.services;

import com.example.gradle.gradledemo.data.User;

import java.util.Set;

public interface UserService {
    User getUserByName(String userName);

    Set<User> getAll();

    Integer save(User user);
}