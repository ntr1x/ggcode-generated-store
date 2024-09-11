package com.example.gateway.events.listener;

import com.example.gateway.events.service.PublicDispatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ntr1x.common.events.CloudEventsConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component("gatewayEvents.publicDispatchListener")
@RequiredArgsConstructor
public class PublicDispatchListener {
    private final PublicDispatchService dispatchService;

    @KafkaListener(
        containerFactory = CloudEventsConstants.CONTAINER_FACTORY_CLOUD_EVENT,
        groupId = "${app.gateway_events.listener.public_dispatch.group_id:gateway_events.public_dispatch}",
        topicPattern = "${app.gateway_events.listener.public_dispatch.topic_pattern:public_dispatch}"
    )
    public void listen(ConsumerRecord<String, CloudEvent> record, Acknowledgment ack) {
        try {
            log.info("Attempt to process public_dispatch cloud event");
            if (dispatchService.processDispatch(record)) {
                log.info("Successfully processed public_dispatch cloud event");
            } else {

                log.info("Successfully processed DISPATCHED or FAILED public_dispatch cloud event");
            }
        } catch (IllegalArgumentException | JsonProcessingException e) {
            log.error("Failed to parse public_dispatch model", e);
        } catch (Exception e) {
            log.error("Cannot process public_dispatch cloud event", e);
        } finally {
            ack.acknowledge();
        }
    }
}
