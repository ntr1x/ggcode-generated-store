package com.example.service.payments.response.profile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.payments.model.profile.ProfilePublicFavoriteModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface ProfilePublicFavoriteResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicFavoriteResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private ProfilePublicFavoriteModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicFavoriteResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private ProfilePublicFavoriteModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicFavoriteResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private ProfilePublicFavoriteModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicFavoriteResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private ProfilePublicFavoriteModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicFavoriteResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<ProfilePublicFavoriteModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicFavoriteResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private ProfilePublicFavoriteModel removed;

        @JsonView(Views.Default.class)
        private ProfilePublicFavoriteModel created;
    }
}
