package com.example.service.uploads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceUploadsSuite.class)
public class ServiceUploadsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUploadsApplication.class, args);
    }
}
