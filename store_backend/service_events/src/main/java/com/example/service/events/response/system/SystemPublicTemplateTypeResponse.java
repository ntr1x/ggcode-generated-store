package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.events.model.system.SystemPublicTemplateTypeModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicTemplateTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateTypeResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateTypeResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicTemplateTypeModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicTemplateTypeModel created;
    }
}
