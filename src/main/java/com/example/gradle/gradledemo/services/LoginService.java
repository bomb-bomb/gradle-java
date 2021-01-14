package com.example.gradle.gradledemo.services;

import com.example.gradle.gradledemo.data.User;

import java.sql.SQLException;
import java.util.Set;

public interface LoginService{
    User getUserByName(String userName) throws SQLException;

    Set<User> getAll() throws SQLException;
}