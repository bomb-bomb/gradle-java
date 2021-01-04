package com.example.gradle.gradledemo.accessingdatamysql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // This tells Hibernate to make a table out of this class
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Size(min = 2, max = 5, message = "名字字数在2-5间")
    @NotNull
    private String name;

    @Size(min = 2, max = 3, message = "邮箱字数在2-5间")
    @Email(message = "email error")
    private String email;
}