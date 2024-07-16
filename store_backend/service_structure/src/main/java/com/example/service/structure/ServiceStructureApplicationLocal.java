package com.example.service.structure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Use this main class to launch the application locally.
 */
@SpringBootApplication
@Import(ServiceStructureSuite.class)
public class ServiceStructureApplicationLocal {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceStructureApplicationLocal.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }
}
