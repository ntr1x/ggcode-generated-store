package com.example.gateway.payments.listener;

import io.cloudevents.CloudEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ntr1x.common.events.CloudEventsConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component("gatewayPayments.publicPaymentListener")
public class PublicPaymentListener {

    @KafkaListener(
        containerFactory = CloudEventsConstants.CONTAINER_FACTORY_CLOUD_EVENT,
        groupId = "${app.gateway_payments.listener.public_payment.group_id:gateway_payments.public_payment}",
        topicPattern = "${app.gateway_payments.listener.public_payment.topic_pattern:public_payment}"
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
