package com.example.service.security.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ServiceSecurityExchange {

    @Bean
    @ConditionalOnMissingBean(name = "securityEventTopic")
    public NewTopic securityEventTopic() {
        return TopicBuilder
                .name("security_event")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
