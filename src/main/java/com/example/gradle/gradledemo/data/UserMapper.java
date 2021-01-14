package com.example.gradle.gradledemo.data;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserMapper {
    User Sel(String username);

    Set<User> all();
}