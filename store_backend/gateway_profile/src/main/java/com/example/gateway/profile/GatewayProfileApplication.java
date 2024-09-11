package com.example.gateway.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GatewayProfileSuite.class)
public class GatewayProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayProfileApplication.class, args);
    }
}
