package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicAttachmentTypeEntity;
import com.example.service.catalog.model.system.SystemPublicAttachmentTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicAttachmentTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicAttachmentTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicAttachmentTypeEntity, SystemPublicAttachmentTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicAttachmentTypeModel convert(PublicAttachmentTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicAttachmentTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
