package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.events.model.system.SystemPublicTemplateModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicTemplateResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicTemplateModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicTemplateModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicTemplateModel created;
    }
}
