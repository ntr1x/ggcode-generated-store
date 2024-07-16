package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicPromotionTypeModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicPromotionTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTypeResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTypeResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicPromotionTypeModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicPromotionTypeModel created;
    }
}
