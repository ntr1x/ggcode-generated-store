package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.events.model.system.SystemPublicDispatchTypeModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicDispatchTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchTypeResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchTypeResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicDispatchTypeModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicDispatchTypeModel created;
    }
}
