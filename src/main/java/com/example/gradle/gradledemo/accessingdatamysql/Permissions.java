package com.example.gradle.gradledemo.accessingdatamysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Permissions {
    private String id;
    private String permissionsName;
}