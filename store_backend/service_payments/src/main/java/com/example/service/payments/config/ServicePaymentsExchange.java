package com.example.service.payments.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ServicePaymentsExchange {
    
    @Bean
    @ConditionalOnMissingBean
    public NewTopic publicOrderTopic() {
        return TopicBuilder
                .name("public_order")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean
    public NewTopic publicPaymentTopic() {
        return TopicBuilder
                .name("public_payment")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
