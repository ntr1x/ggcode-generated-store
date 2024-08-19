package com.example.gateway.events.config.transport;

import com.example.gateway.events.property.HuaweiProperty;
import com.example.gateway.events.service.notification.push.HuaweiMessaging;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.huawei")
public class HuaweiConfig {

    @Valid
    private HuaweiProperty push;

    @Bean
    HuaweiMessaging huaweiMessaging() {
        return push == null ? null : new HuaweiMessaging(push);
    }
}
