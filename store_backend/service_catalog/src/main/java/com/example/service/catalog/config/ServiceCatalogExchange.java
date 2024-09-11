package com.example.service.catalog.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ServiceCatalogExchange {
    
    @Bean
    @ConditionalOnMissingBean(name = "publicCategoryTopic")
    public NewTopic publicCategoryTopic() {
        return TopicBuilder
                .name("public_category")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    @ConditionalOnMissingBean(name = "publicProductTopic")
    public NewTopic publicProductTopic() {
        return TopicBuilder
                .name("public_product")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
