package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicEventTopicInfoEntity;
import com.example.service.events.model.PublicEventTopicInfoModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicEventTopicInfoEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicEventTopicInfoEntityConverter.toModel")
    class ToModel implements AppConverter<PublicEventTopicInfoEntity, PublicEventTopicInfoModel> {
        private final ApplicationContext applicationContext;

        public PublicEventTopicInfoModel convert(PublicEventTopicInfoEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicEventTopicInfoModel.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
