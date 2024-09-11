package com.example.gateway.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Use this main class to launch the application locally.
 */
@SpringBootApplication
@Import(GatewayProfileSuite.class)
public class GatewayProfileApplicationLocal {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GatewayProfileApplicationLocal.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }
}
