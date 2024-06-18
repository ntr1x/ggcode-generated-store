package com.example.gateway.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GatewayEventsSuite.class)
public class GatewayEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayEventsApplication.class, args);
    }
}
