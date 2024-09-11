package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicPromotionTargetModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicPromotionTargetResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTargetModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTargetModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTargetModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTargetModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicPromotionTargetModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicPromotionTargetModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicPromotionTargetModel created;
    }
}
