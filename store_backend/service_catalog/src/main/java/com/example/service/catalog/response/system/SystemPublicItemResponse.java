package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicItemModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicItemResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicItemResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicItemModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicItemResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicItemModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicItemResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicItemModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicItemResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicItemModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicItemResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicItemModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicItemResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicItemModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicItemModel created;
    }
}
