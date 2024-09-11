package com.example.service.profile.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.entity.PublicAgentEntity;
import com.example.service.profile.model.system.SystemPublicAgentModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicAgentEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceProfile.systemPublicAgentEntityConverter.toModel")
    class ToModel implements AppConverter<PublicAgentEntity, SystemPublicAgentModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicAgentModel convert(PublicAgentEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicAgentModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .kind(source.getKind())
                    .title(source.getTitle())
                    .inn(source.getInn())
                    .kpp(source.getKpp())
                    .bic(source.getBic())
                    .bank(source.getBank())
                    .okved(source.getOkved())
                    .address(source.getAddress())
                    .phone(source.getPhone())
                    .email(source.getEmail())
                    .registrationAddress(source.getRegistrationAddress())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.profile.model.PublicCustomerModel.class))
                    .build();
        }
    }
}
