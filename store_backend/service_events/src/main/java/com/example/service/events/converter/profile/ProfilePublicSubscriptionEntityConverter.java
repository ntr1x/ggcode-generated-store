package com.example.service.events.converter.profile;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicSubscriptionEntity;
import com.example.service.events.model.profile.ProfilePublicSubscriptionModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface ProfilePublicSubscriptionEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.profilePublicSubscriptionEntityConverter.toModel")
    class ToModel implements AppConverter<PublicSubscriptionEntity, ProfilePublicSubscriptionModel> {
        private final ApplicationContext applicationContext;

        public ProfilePublicSubscriptionModel convert(PublicSubscriptionEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return ProfilePublicSubscriptionModel.builder()
                    .id(source.getId())
                    .typeId(source.getTypeId())
                    .sessionId(source.getSessionId())
                    .createdAt(source.getCreatedAt())
                    .payload(source.getPayload())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.events.model.PublicCustomerModel.class))
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.events.model.PublicSubscriptionTypeModel.class))
                    .build();
        }
    }
}
