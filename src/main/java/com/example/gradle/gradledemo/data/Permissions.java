package com.example.gradle.gradledemo.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Permissions {
    private String id;
    private String permissionsName;
}