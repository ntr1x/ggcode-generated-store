package com.example.service.catalog.request.system;

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

public interface SystemPublicPromotionRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion:request:create";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> config;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> config;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> config;

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
    @Schema(name = "SystemPublicPromotionRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> config;
    }
}
