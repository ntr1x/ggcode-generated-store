package com.example.service.payments.request.profile;

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

public interface ProfilePublicBasketRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket:context";
        
        private java.util.UUID customerId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket:request:create";
        
        private Optional<java.util.UUID> agentId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> agentId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> agentId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;

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
    @Schema(name = "ProfilePublicBasketRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.util.UUID> agentId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
    }
}
