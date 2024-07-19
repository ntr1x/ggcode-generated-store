package com.example.service.structure.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicGuideEntity;
import com.example.service.structure.model.PublicGuideModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicGuideEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.publicGuideEntityConverter.toModel")
    class ToModel implements AppConverter<PublicGuideEntity, PublicGuideModel> {
        private final ApplicationContext applicationContext;

        public PublicGuideModel convert(PublicGuideEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicGuideModel.builder()
                    .id(source.getId())
                    .subjectId(source.getSubjectId())
                    .name(source.getName())
                    .link(source.getLink())
                    .position(source.getPosition())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .subject(conversionService.convert(
                            source.getSubject(),
                            com.example.service.structure.model.PublicGuideSubjectModel.class))
                    .build();
        }
    }
}
