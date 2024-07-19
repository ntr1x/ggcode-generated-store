package com.example.service.structure.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicGuideSubjectEntity;
import com.example.service.structure.model.PublicGuideSubjectModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicGuideSubjectEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.publicGuideSubjectEntityConverter.toModel")
    class ToModel implements AppConverter<PublicGuideSubjectEntity, PublicGuideSubjectModel> {
        private final ApplicationContext applicationContext;

        public PublicGuideSubjectModel convert(PublicGuideSubjectEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicGuideSubjectModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .position(source.getPosition())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .build();
        }
    }
}
