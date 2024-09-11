package com.example.service.payments.converter.profile;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicPaymentEntity;
import com.example.service.payments.model.profile.ProfilePublicPaymentModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface ProfilePublicPaymentEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.profilePublicPaymentEntityConverter.toModel")
    class ToModel implements AppConverter<PublicPaymentEntity, ProfilePublicPaymentModel> {
        private final ApplicationContext applicationContext;

        public ProfilePublicPaymentModel convert(PublicPaymentEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return ProfilePublicPaymentModel.builder()
                    .id(source.getId())
                    .orderId(source.getOrderId())
                    .paymentStatusId(source.getPaymentStatusId())
                    .paymentTypeId(source.getPaymentTypeId())
                    .value(source.getValue())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.payments.model.PublicCustomerModel.class))
                    .order(conversionService.convert(
                            source.getOrder(),
                            com.example.service.payments.model.PublicOrderModel.class))
                    .paymentStatus(conversionService.convert(
                            source.getPaymentStatus(),
                            com.example.service.payments.model.PublicPaymentStatusModel.class))
                    .paymentType(conversionService.convert(
                            source.getPaymentType(),
                            com.example.service.payments.model.PublicPaymentTypeModel.class))
                    .build();
        }
    }
}
