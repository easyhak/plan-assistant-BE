package com.example.planassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing

public class PlanAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanAssistantApplication.class, args);
    }

}
