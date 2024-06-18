package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.events.model.system.SystemPublicEventSourceInfoModel;
import org.ntr1x.common.api.views.Views;

public interface SystemPublicEventSourceInfoResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventSourceInfoResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicEventSourceInfoModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventSourceInfoResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicEventSourceInfoModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventSourceInfoResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicEventSourceInfoModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventSourceInfoResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicEventSourceInfoModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventSourceInfoResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicEventSourceInfoModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicEventSourceInfoModel created;
    }
}
