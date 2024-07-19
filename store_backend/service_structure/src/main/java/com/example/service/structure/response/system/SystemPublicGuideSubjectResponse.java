package com.example.service.structure.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.structure.model.system.SystemPublicGuideSubjectModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicGuideSubjectResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideSubjectResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicGuideSubjectModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideSubjectResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicGuideSubjectModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideSubjectResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicGuideSubjectModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideSubjectResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicGuideSubjectModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideSubjectResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicGuideSubjectModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideSubjectResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicGuideSubjectModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicGuideSubjectModel created;
    }
}
