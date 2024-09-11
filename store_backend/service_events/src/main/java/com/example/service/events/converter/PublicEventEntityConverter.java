package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicEventEntity;
import com.example.service.events.model.PublicEventModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicEventEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicEventEntityConverter.toModel")
    class ToModel implements AppConverter<PublicEventEntity, PublicEventModel> {
        private final ApplicationContext applicationContext;

        public PublicEventModel convert(PublicEventEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicEventModel.builder()
                    .id(source.getId())
                    .topic(source.getTopic())
                    .contentType(source.getContentType())
                    .ceType(source.getCeType())
                    .ceSource(source.getCeSource())
                    .ceSpecification(source.getCeSpecification())
                    .ceId(source.getCeId())
                    .createdAt(source.getCreatedAt())
                    .payload(source.getPayload())
                    .topicInfo(conversionService.convert(
                            source.getTopicInfo(),
                            com.example.service.events.model.PublicEventTopicInfoModel.class))
                    .ceSourceInfo(conversionService.convert(
                            source.getCeSourceInfo(),
                            com.example.service.events.model.PublicEventSourceInfoModel.class))
                    .ceTypeInfo(conversionService.convert(
                            source.getCeTypeInfo(),
                            com.example.service.events.model.PublicEventTypeInfoModel.class))
                    .build();
        }
    }
}
