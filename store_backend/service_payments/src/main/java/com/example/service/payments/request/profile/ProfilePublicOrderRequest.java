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

public interface ProfilePublicOrderRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicOrderRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "profile_public_order:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicOrderRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "profile_public_order:context";
        
        private java.util.UUID customerId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicOrderRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "profile_public_order:request:create";
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
        
        private Optional<java.util.UUID> basketId;
        
        private Optional<java.util.UUID> agentId;
        
        private Optional<java.lang.Integer> orderTypeId;
        
        private Optional<java.lang.Integer> orderStatusId;
        
        private Optional<java.math.BigDecimal> price;
        
        private Optional<java.math.BigDecimal> spend;
        
        private Optional<java.math.BigDecimal> bonus;
        
        private Optional<java.math.BigDecimal> discount;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicOrderRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "profile_public_order:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
        
        private Optional<java.util.UUID> basketId;
        
        private Optional<java.util.UUID> agentId;
        
        private Optional<java.lang.Integer> orderTypeId;
        
        private Optional<java.lang.Integer> orderStatusId;
        
        private Optional<java.math.BigDecimal> price;
        
        private Optional<java.math.BigDecimal> spend;
        
        private Optional<java.math.BigDecimal> bonus;
        
        private Optional<java.math.BigDecimal> discount;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;

        @Valid
        @Hidden
        @JsonProperty("$where")
        private Collection<@Valid WhereStatement> __where;

        @Valid
        @Hidden
        @JsonProperty("$order")
        private Collection<@Valid OrderStatement> __order;
    }
}
