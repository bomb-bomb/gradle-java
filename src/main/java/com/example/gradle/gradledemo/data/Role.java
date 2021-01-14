package com.example.gradle.gradledemo.data;

import lombok.Data;

import java.util.Collections;
import java.util.Set;

@Data
public class Role {
    private Integer id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions = Collections.EMPTY_SET;
}