package com.example.gateway.events.listener;

import com.example.gateway.events.service.UpdatePhoneService;
import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ntr1x.common.events.CloudEventsConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component("gatewayEvents.updatePhoneListener")
@RequiredArgsConstructor
public class UpdatePhoneListener {
    private final UpdatePhoneService updatePhoneService;


    @KafkaListener(
            containerFactory = CloudEventsConstants.CONTAINER_FACTORY_CLOUD_EVENT,
            groupId = "${app.gateway_events.listener.update_phone.group_id:gateway_events.update_phone}",
            topicPattern = "${app.gateway_events.listener.update_phone.topic_pattern:update_phone}"
    )
    public void listen(ConsumerRecord<String, CloudEvent> record, Acknowledgment ack) {
        try {
            log.info("Attempt to process update_phone cloud event");
            updatePhoneService.processUpdatePhone(record);
            log.info("Successfully processed update_phone cloud event");
        } catch (Exception e) {
            log.error("Cannot process update_phone cloud event", e);
        } finally {
            ack.acknowledge();
        }
    }
}
