package com.alloymobile.drawing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class DrawingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrawingApplication.class, args);
    }

}
