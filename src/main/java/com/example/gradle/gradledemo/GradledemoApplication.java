package com.example.gradle.gradledemo;

import com.example.gradle.gradledemo.storage.StorageProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SuppressWarnings("ALL")
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@MapperScan("com/example/gradle/gradledemo/data")
public class GradledemoApplication {
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(GradledemoApplication.class, args);
	}
}
