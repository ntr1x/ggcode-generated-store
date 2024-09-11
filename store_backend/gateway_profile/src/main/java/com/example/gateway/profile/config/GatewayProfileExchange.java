package com.example.gateway.profile.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class GatewayProfileExchange {
    
    @Bean
    @ConditionalOnMissingBean(name = "publicCustomersTopic")
    public NewTopic publicCustomersTopic() {
        return TopicBuilder
                .name("public_customers")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean(name = "verifyPhoneTopic")
    public NewTopic verifyPhoneTopic() {
        return TopicBuilder
                .name("verify_phone")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean(name = "verifyEmailTopic")
    public NewTopic verifyEmailTopic() {
        return TopicBuilder
                .name("verify_email")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean(name = "updatePhoneTopic")
    public NewTopic updatePhoneTopic() {
        return TopicBuilder
                .name("update_phone")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean(name = "updateEmailTopic")
    public NewTopic updateEmailTopic() {
        return TopicBuilder
                .name("update_email")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
