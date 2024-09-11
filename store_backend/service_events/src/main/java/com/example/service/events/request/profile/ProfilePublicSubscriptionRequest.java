package com.example.service.events.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.ntr1x.common.jpa.criteria.OrderStatement;
import org.ntr1x.common.jpa.criteria.WhereStatement;

import org.springdoc.core.annotations.ParameterObject;

import java.util.Collection;
import java.util.Optional;

public interface ProfilePublicSubscriptionRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "profile_public_subscription:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "profile_public_subscription:context";
        
        private java.util.UUID customerId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "profile_public_subscription:request:create";
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "profile_public_subscription:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "profile_public_subscription:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;

        @Valid
        @Hidden
        @JsonProperty("$where")
        private Collection<@Valid WhereStatement> __where;

        @Valid
        @Hidden
        @JsonProperty("$order")
        private Collection<@Valid OrderStatement> __order;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicSubscriptionRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "profile_public_subscription:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
}
