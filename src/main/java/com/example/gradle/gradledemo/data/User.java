package com.example.gradle.gradledemo.data;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;

    private Set<Role> roles;
}