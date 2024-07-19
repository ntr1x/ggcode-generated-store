package com.example.service.structure.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.structure.model.system.SystemPublicGuideModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicGuideResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicGuideModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicGuideModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicGuideModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicGuideModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicGuideModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicGuideModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicGuideModel created;
    }
}
