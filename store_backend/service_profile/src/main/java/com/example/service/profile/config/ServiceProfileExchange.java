package com.example.service.profile.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ServiceProfileExchange {
    
    @Bean
    @ConditionalOnMissingBean
    public NewTopic publicCustomersTopic() {
        return TopicBuilder
                .name("public_customers")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean
    public NewTopic verifyPhoneTopic() {
        return TopicBuilder
                .name("verify_phone")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean
    public NewTopic verifyEmailTopic() {
        return TopicBuilder
                .name("verify_email")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean
    public NewTopic updatePhoneTopic() {
        return TopicBuilder
                .name("update_phone")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean
    public NewTopic updateEmailTopic() {
        return TopicBuilder
                .name("update_email")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
