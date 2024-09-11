package com.example.gateway.events.config;

import com.example.gateway.events.config.transport.FirebaseConfig;
import com.example.gateway.events.config.transport.HuaweiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ FirebaseConfig.class, HuaweiConfig.class })
public class GatewayEventsConfig {
}
