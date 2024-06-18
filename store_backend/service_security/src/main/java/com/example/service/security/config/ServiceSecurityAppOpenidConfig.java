package com.example.service.security.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.example.service.security.property.AppOpenidClientProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.openid")
public class ServiceSecurityAppOpenidConfig {
    @NotNull
    private AppOpenidClientProperty client;
}
