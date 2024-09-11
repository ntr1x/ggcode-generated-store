package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.events.model.system.SystemPublicEventTypeInfoModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicEventTypeInfoResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicEventTypeInfoModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicEventTypeInfoModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicEventTypeInfoModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicEventTypeInfoModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicEventTypeInfoModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicEventTypeInfoModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicEventTypeInfoModel created;
    }
}
