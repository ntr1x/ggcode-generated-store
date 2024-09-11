package com.example.service.payments.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.payments.model.system.SystemPublicOrderModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicOrderResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicOrderModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicOrderModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicOrderModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicOrderModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicOrderModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicOrderModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicOrderModel created;
    }
}
