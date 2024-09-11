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

public interface SystemPublicEventRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_event:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_event:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_event:request:create";
        
        private Optional<java.lang.String> topic;
        
        private Optional<java.lang.String> contentType;
        
        private Optional<java.lang.String> ceType;
        
        private Optional<java.lang.String> ceSource;
        
        private Optional<java.lang.String> ceSpecification;
        
        private Optional<java.lang.String> ceId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_event:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> topic;
        
        private Optional<java.lang.String> contentType;
        
        private Optional<java.lang.String> ceType;
        
        private Optional<java.lang.String> ceSource;
        
        private Optional<java.lang.String> ceSpecification;
        
        private Optional<java.lang.String> ceId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_event:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> topic;
        
        private Optional<java.lang.String> contentType;
        
        private Optional<java.lang.String> ceType;
        
        private Optional<java.lang.String> ceSource;
        
        private Optional<java.lang.String> ceSpecification;
        
        private Optional<java.lang.String> ceId;
        
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
    @Schema(name = "SystemPublicEventRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_event:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.String> topic;
        
        private Optional<java.lang.String> contentType;
        
        private Optional<java.lang.String> ceType;
        
        private Optional<java.lang.String> ceSource;
        
        private Optional<java.lang.String> ceSpecification;
        
        private Optional<java.lang.String> ceId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
}
