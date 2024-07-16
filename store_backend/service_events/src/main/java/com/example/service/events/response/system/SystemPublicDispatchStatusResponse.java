package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.events.model.system.SystemPublicDispatchStatusModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicDispatchStatusResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchStatusResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchStatusModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchStatusResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchStatusModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchStatusResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchStatusModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchStatusResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchStatusModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchStatusResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicDispatchStatusModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchStatusResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchStatusModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicDispatchStatusModel created;
    }
}
