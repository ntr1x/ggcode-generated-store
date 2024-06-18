package com.example.service.catalog.response.anonymous;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.catalog.model.anonymous.AnonymousPublicRegionModel;
import org.ntr1x.common.api.views.Views;

public interface AnonymousPublicRegionResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicRegionResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private AnonymousPublicRegionModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicRegionResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private AnonymousPublicRegionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicRegionResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private AnonymousPublicRegionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicRegionResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private AnonymousPublicRegionModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicRegionResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private AnonymousPublicRegionModel removed;

        @JsonView(Views.Default.class)
        private AnonymousPublicRegionModel created;
    }
}
