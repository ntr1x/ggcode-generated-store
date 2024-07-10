package com.example.service.catalog.request.anonymous;

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

public interface AnonymousPublicItemRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "anonymous_public_item:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "anonymous_public_item:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicItemRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "anonymous_public_item:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.util.UUID> shopId;
        
        private Optional<java.lang.String> ean13;
        
        private Optional<java.lang.String> code128;

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
