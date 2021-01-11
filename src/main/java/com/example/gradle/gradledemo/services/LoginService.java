package com.example.gradle.gradledemo.services;

import com.example.gradle.gradledemo.accessingdatamysql.User;

public interface LoginService{
    User getUserByName(String userName);
}