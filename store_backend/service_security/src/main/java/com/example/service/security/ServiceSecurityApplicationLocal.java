package com.example.service.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Use this main class to launch the application locally.
 */
@SpringBootApplication
@Import(ServiceSecuritySuite.class)
public class ServiceSecurityApplicationLocal {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceSecurityApplicationLocal.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }
}
