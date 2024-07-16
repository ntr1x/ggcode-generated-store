package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicImageTypeModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicImageTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicImageTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicImageTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicImageTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicImageTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicImageTypeModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicImageTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicImageTypeModel created;
    }
}
