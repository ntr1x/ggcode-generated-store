package com.example.service.catalog.response.anonymous;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.catalog.model.anonymous.AnonymousPublicItemModel;
import org.ntr1x.common.api.views.Views;

public interface AnonymousPublicItemResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private AnonymousPublicItemModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private AnonymousPublicItemModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private AnonymousPublicItemModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private AnonymousPublicItemModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private AnonymousPublicItemModel removed;

        @JsonView(Views.Default.class)
        private AnonymousPublicItemModel created;
    }
}
