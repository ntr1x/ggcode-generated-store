package com.example.service.structure.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.structure.model.system.SystemPublicNetworkTypeModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicNetworkTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkTypeResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkTypeResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicNetworkTypeModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicNetworkTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicNetworkTypeModel created;
    }
}
