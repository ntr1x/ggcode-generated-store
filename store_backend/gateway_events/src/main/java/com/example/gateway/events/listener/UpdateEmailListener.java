package com.example.gateway.events.listener;

import io.cloudevents.CloudEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ntr1x.common.events.CloudEventsConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component("gatewayEvents.updateEmailListener")
public class UpdateEmailListener {

    @KafkaListener(
        containerFactory = CloudEventsConstants.CONTAINER_FACTORY_CLOUD_EVENT,
        groupId = "${app.gateway_events.listener.update_email.group_id:gateway_events.update_email}",
        topicPattern = "${app.gateway_events.listener.update_email.topic_pattern:update_email}"
    )
    public void listen(ConsumerRecord<String, CloudEvent> record, Acknowledgment ack) {
        try {
            log.info("Attempt to process cloud event");

            // TODO Implement

            ack.acknowledge();
        } catch (Exception e) {
            log.error("Cannot process cloud event", e);
        }
    }
}
