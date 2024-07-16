package com.example.service.payments.response.profile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.payments.model.profile.ProfilePublicBasketModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface ProfilePublicBasketResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private ProfilePublicBasketModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private ProfilePublicBasketModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private ProfilePublicBasketModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private ProfilePublicBasketModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<ProfilePublicBasketModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private ProfilePublicBasketModel removed;

        @JsonView(Views.Default.class)
        private ProfilePublicBasketModel created;
    }
}
