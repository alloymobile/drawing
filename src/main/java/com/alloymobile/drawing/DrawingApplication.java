package com.alloymobile.drawing;

import com.alloymobile.drawing.application.config.EntityAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableReactiveMongoAuditing
public class DrawingApplication {
    @Bean
    public ReactiveAuditorAware<String> auditorAware(){
        return new EntityAuditing();
    }
    public static void main(String[] args) {
        SpringApplication.run(DrawingApplication.class, args);
    }
}
