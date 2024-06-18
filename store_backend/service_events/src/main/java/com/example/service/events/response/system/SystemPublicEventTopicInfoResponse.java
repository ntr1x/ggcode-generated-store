package com.example.service.events.response.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.events.model.system.SystemPublicEventTopicInfoModel;
import org.ntr1x.common.api.views.Views;

public interface SystemPublicEventTopicInfoResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTopicInfoResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicEventTopicInfoModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTopicInfoResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicEventTopicInfoModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTopicInfoResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicEventTopicInfoModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTopicInfoResponseUpdate")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicEventTopicInfoModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTopicInfoResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicEventTopicInfoModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicEventTopicInfoModel created;
    }
}
