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

public interface ProfilePublicPaymentRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicPaymentRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "profile_public_payment:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicPaymentRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "profile_public_payment:context";
        
        private java.util.UUID customerId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicPaymentRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "profile_public_payment:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> orderId;
        
        private Optional<java.lang.Integer> paymentStatusId;
        
        private Optional<java.lang.Integer> paymentTypeId;
        
        private Optional<java.math.BigDecimal> value;
        
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
