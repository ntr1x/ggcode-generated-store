package com.example.gateway.events.service;

import com.example.gateway.events.converter.PublicDispatchEntityConverter;
import com.example.gateway.events.entity.PublicDispatchEntity;
import com.example.gateway.events.entity.PublicSubscriptionEntity;
import com.example.gateway.events.model.PublicDispatchModel;
import com.example.gateway.events.repository.PublicDispatchRepository;
import com.example.gateway.events.repository.PublicSubscriptionRepository;
import com.example.gateway.events.service.notification.NotificationService;
import com.example.gateway.events.util.CloudEventParser;
import com.example.shared.api.reference.PublicDispatchStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.domain.Example;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicDispatchService {
    private static final String TOPIC_PUBLIC_DISPATCH = "public_dispatch";
    private static final String EVENT_TYPE_CREATED = "created";
    private static final String EVENT_TYPE_UPDATED = "updated";
    private static final String EVENT_TYPE_SUGGEST = "suggest";

    private final PublicDispatchRepository dispatchRepository;
    private final PublicSubscriptionRepository subscriptionRepository;
    private final NotificationService notificationService;
    private final KafkaTemplate<String, CloudEvent> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final PublicDispatchEntityConverter.ToModel dispatchConverter;
    private final CloudEventParser cloudEventParser;

    public void processDispatch(ConsumerRecord<String, CloudEvent> record) throws JsonProcessingException {
        CloudEvent cloudEvent = record.value();
        PublicDispatchModel dispatchModel = cloudEventParser.parseCloudEventData(cloudEvent, PublicDispatchModel.class);
        if (dispatchModel == null) {
            log.warn("Failed to parse cloud event: {}", cloudEvent);
            return;
        }

        String ceType = cloudEvent.getType();
        Integer statusId = dispatchModel.getStatusId();

        if (ceType.equals(EVENT_TYPE_SUGGEST) && (statusId == null || statusId == 0)) {
            processSuggestDispatch(dispatchModel, cloudEvent);
        } else if (statusId != null && ceType.equals(EVENT_TYPE_CREATED) && PublicDispatchStatus.NEW.getCode() == (statusId)) {
            processNewDispatch(dispatchModel, cloudEvent);
        }
    }

    private void processNewDispatch(PublicDispatchModel dispatchModel, CloudEvent cloudEvent) throws JsonProcessingException {
        PublicDispatchEntity probe = new PublicDispatchEntity();

        probe.setCustomerId(dispatchModel.getCustomerId());
        probe.setSubscriptionId(dispatchModel.getSubscriptionId());
        probe.setStatusId(dispatchModel.getStatusId());
        probe.setTypeId(dispatchModel.getTypeId());

        Example<PublicDispatchEntity> example = Example.of(probe);
        List<PublicDispatchEntity> matchingDispatches = dispatchRepository.findAll(example);

        JsonNode payload = dispatchModel.getPayload();
        for (PublicDispatchEntity dispatch : matchingDispatches) {
            Set<String> deviceTokens = getDeviceTokensFromSubscription(dispatch);
            if (!deviceTokens.isEmpty()) {
                try {
                    for (String deviceToken : deviceTokens) {
                        String title = payload.has("title") ? payload.get("title").asText() : null;
                        String body = payload.has("body") ? payload.get("body").asText() : null;
                        notificationService.sendNotification(deviceToken, title, body, dispatch.getTypeId());
                        updateDispatchStatus(dispatch, PublicDispatchStatus.DISPATCHED, cloudEvent);
                    }
                } catch (Exception e) {
                    log.error("Failed to process dispatch: {}", dispatch.getId(), e);
                    updateDispatchStatus(dispatch, PublicDispatchStatus.FAILED, cloudEvent);
                }
            } else {
                log.warn("No device tokens found for dispatch: {}", dispatch.getId());
                updateDispatchStatus(dispatch, PublicDispatchStatus.FAILED, cloudEvent);
            }
        }
    }

    private void processSuggestDispatch(PublicDispatchModel dispatchModel, CloudEvent cloudEvent) throws JsonProcessingException {
        PublicDispatchEntity newDispatch = new PublicDispatchEntity();
        newDispatch.setId(UUID.randomUUID());
        newDispatch.setCustomerId(dispatchModel.getCustomerId());
        newDispatch.setSubscriptionId(dispatchModel.getSubscriptionId());
        newDispatch.setTypeId(dispatchModel.getTypeId());
        newDispatch.setSessionId(dispatchModel.getSessionId());
        newDispatch.setPayload(dispatchModel.getPayload());
        newDispatch.setCreatedAt(dispatchModel.getCreatedAt());
        newDispatch.setUpdatedAt(dispatchModel.getUpdatedAt());
        newDispatch.setStatusId(PublicDispatchStatus.NEW.getCode());

        dispatchRepository.saveAndFlush(newDispatch);
        CloudEvent newCloudEvent = createCloudEvent(dispatchConverter.convert(newDispatch), cloudEvent, EVENT_TYPE_CREATED);
        kafkaTemplate.send(TOPIC_PUBLIC_DISPATCH, newCloudEvent);

        log.info("Saved new dispatch and published to Kafka: {}", newDispatch.getId());
    }

    private void updateDispatchStatus(PublicDispatchEntity dispatchEntity, PublicDispatchStatus newStatus, CloudEvent cloudEvent) throws JsonProcessingException {
        dispatchEntity.setStatusId((newStatus.getCode()));
        PublicDispatchModel dispatchModel = dispatchConverter.convert(dispatchEntity);

        kafkaTemplate.send(TOPIC_PUBLIC_DISPATCH, createCloudEvent(dispatchModel, cloudEvent, EVENT_TYPE_UPDATED));
        dispatchRepository.save(dispatchEntity);
        log.info("Updated dispatch status to {} for dispatch: {}", newStatus, dispatchEntity.getId());
    }

    private CloudEvent createCloudEvent(PublicDispatchModel model, CloudEvent sourceEvent, String eventType) throws JsonProcessingException {
        return CloudEventBuilder.v1()
                .withId(UUID.randomUUID().toString())
                .withType(eventType)
                .withSource(sourceEvent.getSource())
                .withDataContentType("application/json")
                .withData(objectMapper.writeValueAsBytes(model))
                .build();
    }

    private Set<String> getDeviceTokensFromSubscription(PublicDispatchEntity dispatch) {
        Set<String> deviceTokens = new HashSet<>();
        List<PublicSubscriptionEntity> subscriptions = new ArrayList<>();

        if (dispatch.getCustomerId() != null) {
            PublicSubscriptionEntity probeCustomerId = new PublicSubscriptionEntity();
            probeCustomerId.setCustomerId(dispatch.getCustomerId());
            probeCustomerId.setTypeId(dispatch.getTypeId());
            Example<PublicSubscriptionEntity> exampleCustomerId = Example.of(probeCustomerId);
            subscriptions.addAll(subscriptionRepository.findAll(exampleCustomerId));
        }

        if (dispatch.getSessionId() != null && subscriptions.isEmpty()) {
            PublicSubscriptionEntity probeSessionId = new PublicSubscriptionEntity();
            probeSessionId.setSessionId(dispatch.getSessionId());
            probeSessionId.setTypeId(dispatch.getTypeId());
            Example<PublicSubscriptionEntity> exampleSessionId = Example.of(probeSessionId);
            subscriptions.addAll(subscriptionRepository.findAll(exampleSessionId));
        }

        for (PublicSubscriptionEntity subscription : subscriptions) {
            String token = extractTokenFromSubscriptionPayload(subscription.getPayload());
            if (token != null) {
                deviceTokens.add(token);
            }
        }
        return deviceTokens;
    }

    private String extractTokenFromSubscriptionPayload(JsonNode payload) {
        if (payload != null && payload.has("token")) {
            return payload.get("token").asText();
        }
        log.warn("Token not found in subscription payload");
        return null;
    }
}