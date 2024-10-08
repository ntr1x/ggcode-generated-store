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

public interface ProfilePublicBasketEntryRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketEntryRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket_entry:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketEntryRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket_entry:context";
        
        private java.util.UUID customerId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketEntryRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket_entry:request:create";
        
        private Optional<java.util.UUID> basketId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.lang.Integer> quantity;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketEntryRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket_entry:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> basketId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.lang.Integer> quantity;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicBasketEntryRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket_entry:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> basketId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.lang.Integer> quantity;

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
    @Schema(name = "ProfilePublicBasketEntryRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "profile_public_basket_entry:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.util.UUID> basketId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.lang.Integer> quantity;
    }
}
