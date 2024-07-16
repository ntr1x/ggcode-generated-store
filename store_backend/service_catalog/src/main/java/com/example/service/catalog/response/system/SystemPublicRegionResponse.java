package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicRegionModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicRegionResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicRegionResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicRegionModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicRegionResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicRegionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicRegionResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicRegionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicRegionResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicRegionModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicRegionResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicRegionModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicRegionResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicRegionModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicRegionModel created;
    }
}
