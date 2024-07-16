package com.example.service.catalog.response.anonymous;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.anonymous.AnonymousPublicShopModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface AnonymousPublicShopResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private AnonymousPublicShopModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private AnonymousPublicShopModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private AnonymousPublicShopModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private AnonymousPublicShopModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<AnonymousPublicShopModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private AnonymousPublicShopModel removed;

        @JsonView(Views.Default.class)
        private AnonymousPublicShopModel created;
    }
}
