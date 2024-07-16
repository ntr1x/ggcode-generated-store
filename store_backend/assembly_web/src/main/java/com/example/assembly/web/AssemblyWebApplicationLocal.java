package com.example.assembly.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Use this main class to launch the application locally.
 */
@SpringBootApplication
@Import(AssemblyWebSuite.class)
public class AssemblyWebApplicationLocal {

    public static void main(String[] args) {
        Class<?>[] sources = new Class[]{
                AssemblyWebApplicationLocal.class,
                com.example.service.catalog.ServiceCatalogApplication.class,
                com.example.service.profile.ServiceProfileApplication.class,
                com.example.service.payments.ServicePaymentsApplication.class,
                com.example.service.events.ServiceEventsApplication.class,
                com.example.service.security.ServiceSecurityApplication.class,
                com.example.service.structure.ServiceStructureApplication.class,
                com.example.gateway.events.GatewayEventsApplication.class,
                com.example.gateway.payments.GatewayPaymentsApplication.class,
                com.example.gateway.profile.GatewayProfileApplication.class,
        };

        SpringApplication application = new SpringApplication(sources);
        application.setAdditionalProfiles("local");
        application.run(args);
    }
}
