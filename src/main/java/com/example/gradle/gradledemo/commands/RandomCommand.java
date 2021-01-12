package com.example.gradle.gradledemo.commands;

import com.example.gradle.gradledemo.GradledemoApplication;
import com.example.gradle.gradledemo.models.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RandomCommand {
    private static final Logger log = LoggerFactory.getLogger(GradledemoApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
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
