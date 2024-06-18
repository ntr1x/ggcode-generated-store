package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicEventTopicInfoEntity;
import com.example.service.events.model.system.SystemPublicEventTopicInfoModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicEventTopicInfoEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicEventTopicInfoEntityConverter.toModel")
    class ToModel implements AppConverter<PublicEventTopicInfoEntity, SystemPublicEventTopicInfoModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicEventTopicInfoModel convert(PublicEventTopicInfoEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicEventTopicInfoModel.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
