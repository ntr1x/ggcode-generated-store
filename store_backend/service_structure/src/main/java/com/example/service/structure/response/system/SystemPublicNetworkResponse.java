package com.example.service.structure.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.structure.model.system.SystemPublicNetworkModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicNetworkResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicNetworkModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicNetworkModel created;
    }
}
