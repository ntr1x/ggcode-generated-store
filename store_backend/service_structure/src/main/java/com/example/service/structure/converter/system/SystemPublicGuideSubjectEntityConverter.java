package com.example.service.structure.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicGuideSubjectEntity;
import com.example.service.structure.model.system.SystemPublicGuideSubjectModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicGuideSubjectEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.systemPublicGuideSubjectEntityConverter.toModel")
    class ToModel implements AppConverter<PublicGuideSubjectEntity, SystemPublicGuideSubjectModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicGuideSubjectModel convert(PublicGuideSubjectEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicGuideSubjectModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .position(source.getPosition())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .build();
        }
    }
}
