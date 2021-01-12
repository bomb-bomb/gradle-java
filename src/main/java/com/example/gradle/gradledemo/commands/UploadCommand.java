package com.example.gradle.gradledemo.commands;

import com.example.gradle.gradledemo.storage.FileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadCommand {
    @Bean
    CommandLineRunner init(FileSystemStorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
