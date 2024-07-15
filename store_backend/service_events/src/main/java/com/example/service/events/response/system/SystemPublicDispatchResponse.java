package com.example.service.events.response.system;

import com.example.service.events.model.system.SystemPublicDispatchModel;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicDispatchResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchResponseUpdate")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicDispatchModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicDispatchModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicDispatchModel created;
    }
}
