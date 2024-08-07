package com.example.gateway.events.service;

import com.example.gateway.events.model.UpdatePhoneModel;
import com.example.gateway.events.repository.PublicCustomerRepository;
import com.example.gateway.events.util.CloudEventParser;
import com.example.gateway.events.util.PhoneUtils;
import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdatePhoneService {
    private final CloudEventParser cloudEventParser;
    private final PublicCustomerRepository customerRepository;

    @Transactional
    public void processUpdatePhone(ConsumerRecord<String, CloudEvent> record) {
        CloudEvent cloudEvent = record.value();
        UpdatePhoneModel updatePhoneModel = cloudEventParser.parseCloudEventData(cloudEvent, UpdatePhoneModel.class);
        if (updatePhoneModel == null) {
            throw new IllegalArgumentException("Parsed update_phone model is null");
        }
        String newPhone = updatePhoneModel.getPhone();
        if (!PhoneUtils.validatePhone(newPhone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        customerRepository.updatePhoneNumber(updatePhoneModel.getCustomerId(), PhoneUtils.normalizePhone(newPhone));
    }
}
