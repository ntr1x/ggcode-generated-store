package com.example.service.events.request.system;

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

public interface SystemPublicTemplateRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_template:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_template:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_template:request:create";
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> shapeId;
        
        private Optional<java.lang.String> name;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_template:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> shapeId;
        
        private Optional<java.lang.String> name;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicTemplateRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_template:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> shapeId;
        
        private Optional<java.lang.String> name;
        
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
    @Schema(name = "SystemPublicTemplateRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_template:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> shapeId;
        
        private Optional<java.lang.String> name;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
}
