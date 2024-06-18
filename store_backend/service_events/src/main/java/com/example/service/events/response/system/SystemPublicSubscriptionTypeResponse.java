package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.events.model.system.SystemPublicSubscriptionTypeModel;
import org.ntr1x.common.api.views.Views;

public interface SystemPublicSubscriptionTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionTypeResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicSubscriptionTypeModel created;
    }
}
