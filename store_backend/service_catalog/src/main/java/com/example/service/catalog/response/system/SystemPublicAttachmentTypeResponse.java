package com.example.service.catalog.response.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicAttachmentTypeModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface SystemPublicAttachmentTypeResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private SystemPublicAttachmentTypeModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private SystemPublicAttachmentTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private SystemPublicAttachmentTypeModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private SystemPublicAttachmentTypeModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<SystemPublicAttachmentTypeModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private SystemPublicAttachmentTypeModel removed;

        @JsonView(Views.Default.class)
        private SystemPublicAttachmentTypeModel created;
    }
}
