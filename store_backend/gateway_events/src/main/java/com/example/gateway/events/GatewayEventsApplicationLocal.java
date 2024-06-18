package com.example.gateway.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Use this main class to launch the application locally.
 */
@SpringBootApplication
@Import(GatewayEventsSuite.class)
public class GatewayEventsApplicationLocal {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GatewayEventsApplicationLocal.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }
}
