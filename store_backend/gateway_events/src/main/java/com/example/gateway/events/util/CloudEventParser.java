package com.example.gateway.events.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CloudEventParser {
    private final ObjectMapper objectMapper;

    public <T> T parseCloudEventData(CloudEvent event, Class<T> valueType) {
        CloudEventData data = event.getData();
        if (data == null) {
            log.error("CloudEvent data cannot be null");
            return null;
        }
        if (!MediaType.APPLICATION_JSON_VALUE.equals(event.getDataContentType())) {
            log.error("CloudEvent data must be JSON");
            return null;
        }
        return Try.of(() -> objectMapper.readValue(data.toBytes(), valueType))
                .onFailure(e -> log.error("Failed to parse CloudEvent data: {}", e.getMessage()))
                .getOrNull();
    }
}
