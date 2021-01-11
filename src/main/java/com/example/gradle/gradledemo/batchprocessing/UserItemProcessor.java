package com.example.gradle.gradledemo.batchprocessing;

import com.example.gradle.gradledemo.accessingdatamysql.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public User process(final User person) {

        log.info("Converting (" + person + ") into (");

        return person;
    }
}