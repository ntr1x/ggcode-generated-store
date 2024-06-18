package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.catalog.model.system.SystemPublicShopModel;
import org.ntr1x.common.api.views.Views;

public interface SystemPublicShopResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicShopModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicShopModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicShopModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicShopModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicShopModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicShopModel created;
    }
}
