package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.catalog.model.system.SystemPublicSourceTypeModel;
import org.ntr1x.common.api.views.Views;

public interface SystemPublicSourceTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSourceTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicSourceTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSourceTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicSourceTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSourceTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicSourceTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSourceTypeResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicSourceTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSourceTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicSourceTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicSourceTypeModel created;
    }
}
