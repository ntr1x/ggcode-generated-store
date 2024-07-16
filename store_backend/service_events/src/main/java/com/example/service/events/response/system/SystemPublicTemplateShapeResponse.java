package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.events.model.system.SystemPublicTemplateShapeModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicTemplateShapeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateShapeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateShapeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateShapeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateShapeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateShapeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateShapeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateShapeResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateShapeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateShapeResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicTemplateShapeModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateShapeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateShapeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicTemplateShapeModel created;
    }
}
