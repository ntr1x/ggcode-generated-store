package com.example.service.profile.response.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.profile.model.system.SystemPublicAgentModel;
import org.ntr1x.common.api.views.Views;

public interface SystemPublicAgentResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicAgentModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicAgentModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicAgentModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicAgentModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicAgentModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicAgentModel created;
    }
}
