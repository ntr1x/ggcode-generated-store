package com.example.service.uploads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Use this main class to launch the application locally.
 */
@SpringBootApplication
@Import(ServiceUploadsSuite.class)
public class ServiceUploadsApplicationLocal {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceUploadsApplicationLocal.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }
}
