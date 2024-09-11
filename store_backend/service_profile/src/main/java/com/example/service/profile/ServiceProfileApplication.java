package com.example.service.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceProfileSuite.class)
public class ServiceProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProfileApplication.class, args);
    }
}
