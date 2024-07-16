package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicPromotionModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicPromotionResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicPromotionModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicPromotionModel created;
    }
}
