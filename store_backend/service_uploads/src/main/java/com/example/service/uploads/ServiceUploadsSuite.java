package com.example.service.uploads;

import com.example.service.uploads.config.ServiceUploadsConfig;
import org.ntr1x.common.api.config.CommonBeanConfig;
import org.ntr1x.common.events.config.CloudEventsBeanConfig;
import org.ntr1x.common.events.config.CloudEventsKafkaConfig;
import org.ntr1x.common.minio.config.MinioBeanConfig;
import org.ntr1x.common.minio.config.MinioConfig;
import org.ntr1x.common.web.config.OpenApiConfig;
import org.ntr1x.common.web.config.OpenApiSecurityConfig;
import org.ntr1x.common.web.config.WebMvcConfig;
import org.ntr1x.common.web.config.WebMvcSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CommonBeanConfig.class,
        WebMvcConfig.class,
        WebMvcSecurityConfig.class,
        OpenApiConfig.class,
        OpenApiSecurityConfig.class,
        CloudEventsKafkaConfig.class,
        CloudEventsBeanConfig.class,
        MinioConfig.class,
        MinioBeanConfig.class,
})
@ComponentScan(basePackageClasses = ServiceUploadsConfig.class)
public class ServiceUploadsSuite {
}
