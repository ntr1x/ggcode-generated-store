package com.example.service.profile.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.profile.model.system.SystemPublicCustomerModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicCustomerResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicCustomerModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicCustomerModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicCustomerModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicCustomerModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicCustomerModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicCustomerModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicCustomerModel created;
    }
}
