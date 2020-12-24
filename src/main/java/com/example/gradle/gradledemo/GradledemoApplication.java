package com.example.gradle.gradledemo;

import com.example.gradle.gradledemo.models.Quote;
import com.example.gradle.gradledemo.storage.FileSystemStorageService;
import com.example.gradle.gradledemo.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.boot.CommandLineRunner;

@SuppressWarnings("ALL")
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class GradledemoApplication {

	private static final Logger log = LoggerFactory.getLogger(GradledemoApplication.class);

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(GradledemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);

			log.info(quote != null ? quote.toString() : null);
		};
	}
}
