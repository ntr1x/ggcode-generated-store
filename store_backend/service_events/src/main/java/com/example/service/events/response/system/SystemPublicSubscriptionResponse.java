package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.events.model.system.SystemPublicSubscriptionModel;
import org.ntr1x.common.api.views.Views;

public interface SystemPublicSubscriptionResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionModel created;
    }
}
