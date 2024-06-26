package com.example.service.events.listener;

import com.example.service.events.model.PublicDispatchModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ntr1x.common.events.CloudEventsConstants;
import org.ntr1x.common.ws.model.SocketOutgoingMessage;
import org.ntr1x.common.ws.service.SocketHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.*;

@Slf4j
@Component
public class PublicDispatchListener {

    @Autowired
    private SocketHubService socketHubService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(
            containerFactory = CloudEventsConstants.CONTAINER_FACTORY_CLOUD_EVENT,
            batch = "true",
            groupId = "${app.service_events.listener.public_dispatch.group_id:service_events}",
            topicPattern = "${app.service_events.listener.public_dispatch.topic_pattern:public_dispatch}"
    )
    public void listen(List<ConsumerRecord<String, CloudEvent>> list, Acknowledgment ack) {
        try {
            log.info("Attempt to process cloud events");

            for (ConsumerRecord<String, CloudEvent> record : list) {
                CloudEvent event = record.value();
                CloudEventData data = event.getData();

                if (!MediaType.APPLICATION_JSON_VALUE.equals(event.getDataContentType())) {
                    return;
                }

                Try<PublicDispatchModel> result = Optional
                        .ofNullable(data)
                        .map(d -> Try.of(() -> objectMapper.readValue(
                                new ByteArrayInputStream(d.toBytes()),
                                PublicDispatchModel.class
                        )))
                        .orElse(Try.success(null));

                if (result.isSuccess()) {
                    PublicDispatchModel payload = result.get();

                    Map<String, String> attributes = new LinkedHashMap<>();
                    attributes.put("channel", "public_dispatch");
                    for (String name : event.getAttributeNames()) {
                        Object value = event.getAttribute(name);
                        attributes.put(name, value == null ? null : value.toString());
                    }

                    SocketOutgoingMessage.MESSAGE message = SocketOutgoingMessage.MESSAGE
                            .builder()
                            .attributes(attributes)
                            .payload(payload)
                            .build();

                    socketHubService.dispatch(message, securityExpressionRoot -> {
                        try {
                            if (securityExpressionRoot.hasAuthority("realm:admin")) {
                                return true;
                            }
                            String sessionId = securityExpressionRoot.getClaimAsString("session_id");
                            if (Objects.equals(sessionId, payload.getSessionId())) {
                                return true;
                            }
                            String customerId = securityExpressionRoot.getSubject();
                            if (Objects.equals(UUID.fromString(customerId), payload.getCustomerId())) {
                                return true;
                            }
                        } catch (Exception e) {
                            log.warn("Cannot check authorities", e);
                        }

                        return false;
                    });
                }
            }

        } catch (Exception e) {
            log.error("Cannot process cloud events", e);
        }

        ack.acknowledge(); // always acknowledge
    }
}
