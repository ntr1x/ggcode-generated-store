package com.example.service.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceSecuritySuite.class)
public class ServiceSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSecurityApplication.class, args);
    }
}
