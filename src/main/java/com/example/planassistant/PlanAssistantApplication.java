package com.example.planassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableMongoAuditing
@EnableJpaRepositories(
        basePackages = {
                "com.example.planassistant.repository.mysql"
        }
)
@EnableMongoRepositories(
        basePackages = {
                "com.example.planassistant.repository.mongodb"
        }
)
public class PlanAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanAssistantApplication.class, args);
    }

}
