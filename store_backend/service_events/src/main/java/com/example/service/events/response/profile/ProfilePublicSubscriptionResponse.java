package com.example.service.events.response.profile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.events.model.profile.ProfilePublicSubscriptionModel;

import org.ntr1x.common.api.views.Views;

import java.util.Collection;

public interface ProfilePublicSubscriptionResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionResponseCreate")
    class Create {
        @JsonView(Views.Default.class)
        private ProfilePublicSubscriptionModel created;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionResponseUpdate")
    class Update {
        @JsonView(Views.Default.class)
        private ProfilePublicSubscriptionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionResponseUpsert")
    class Upsert {
        @JsonView(Views.Default.class)
        private ProfilePublicSubscriptionModel updated;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionResponseRemove")
    class Remove {
        @JsonView(Views.Default.class)
        private ProfilePublicSubscriptionModel removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionResponseRemoveAll")
    class RemoveAll {
        @JsonView(Views.Default.class)
        private Collection<ProfilePublicSubscriptionModel> removed;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionResponseReplace")
    class Replace {
        @JsonView(Views.Default.class)
        private ProfilePublicSubscriptionModel removed;

        @JsonView(Views.Default.class)
        private ProfilePublicSubscriptionModel created;
    }
}
