package com.example.shared.api;

import com.example.shared.api.config.SharedApiConfig;
import org.ntr1x.common.api.config.CommonBeanConfig;
import org.ntr1x.common.events.config.CloudEventsBeanConfig;
import org.ntr1x.common.events.config.CloudEventsKafkaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CommonBeanConfig.class,
        CloudEventsKafkaConfig.class,
        CloudEventsBeanConfig.class,
})
@ComponentScan(basePackageClasses = SharedApiConfig.class)
public class SharedApiSuite {
}
