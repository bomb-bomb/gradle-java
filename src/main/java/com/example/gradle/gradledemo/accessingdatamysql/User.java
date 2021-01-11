package com.example.gradle.gradledemo.accessingdatamysql;

import com.github.braisdom.objsql.annotations.DomainModel;
import com.github.braisdom.objsql.annotations.Queryable;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@DomainModel
public class User {
    @Queryable
    @Size(min = 2, max = 5, message = "名字字数在2-5间")
    @NotNull
    private String name;

    @Size(min = 2, max = 20, message = "邮箱字数在2-20间")
    @Email(message = "email error")
    private String email;

    private String password;

    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}