package com.example.service.structure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceStructureSuite.class)
public class ServiceStructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStructureApplication.class, args);
    }
}
