package com.example.service.catalog.response.anonymous;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.anonymous.AnonymousPublicPromotionModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface AnonymousPublicPromotionResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicPromotionResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private AnonymousPublicPromotionModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicPromotionResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private AnonymousPublicPromotionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicPromotionResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private AnonymousPublicPromotionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicPromotionResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private AnonymousPublicPromotionModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicPromotionResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<AnonymousPublicPromotionModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicPromotionResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private AnonymousPublicPromotionModel removed;

        @JsonView(Views.Default.class)
        private AnonymousPublicPromotionModel created;
    }
}
