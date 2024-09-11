package com.example.service.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Use this main class to launch the application locally.
 */
@SpringBootApplication
@Import(ServiceProfileSuite.class)
public class ServiceProfileApplicationLocal {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceProfileApplicationLocal.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }
}
