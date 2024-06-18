package com.example.service.profile.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.entity.PublicCustomerBonusEntity;
import com.example.service.profile.model.PublicCustomerBonusModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicCustomerBonusEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceProfile.publicCustomerBonusEntityConverter.toModel")
    class ToModel implements AppConverter<PublicCustomerBonusEntity, PublicCustomerBonusModel> {
        private final ApplicationContext applicationContext;

        public PublicCustomerBonusModel convert(PublicCustomerBonusEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicCustomerBonusModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .orderDiscountId(source.getOrderDiscountId())
                    .bonus(source.getBonus())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.profile.model.PublicCustomerModel.class))
                    .build();
        }
    }
}
