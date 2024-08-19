package com.example.service.structure.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicGuideEntity;
import com.example.service.structure.model.system.SystemPublicGuideModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicGuideEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.systemPublicGuideEntityConverter.toModel")
    class ToModel implements AppConverter<PublicGuideEntity, SystemPublicGuideModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicGuideModel convert(PublicGuideEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicGuideModel.builder()
                    .id(source.getId())
                    .subjectId(source.getSubjectId())
                    .name(source.getName())
                    .link(source.getLink())
                    .description(source.getDescription())
                    .attachment(source.getAttachment())
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
