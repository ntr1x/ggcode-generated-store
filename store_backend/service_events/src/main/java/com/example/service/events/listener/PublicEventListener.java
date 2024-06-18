package com.example.service.events.listener;

import io.cloudevents.CloudEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ntr1x.common.events.CloudEventsConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PublicEventListener {

    @KafkaListener(
            containerFactory = CloudEventsConstants.CONTAINER_FACTORY_CLOUD_EVENT,
            batch = "true",
            groupId = "service_events",
            topicPattern = "public_.*"
    )
    public void listen(List<ConsumerRecord<String, CloudEvent>> list, Acknowledgment ack) {
        try {
            log.info("Attempt to process cloud event");

            // TODO Implement

            ack.acknowledge();
        } catch (Exception e) {
            log.error("Cannot process cloud event", e);
        }
    }
}
