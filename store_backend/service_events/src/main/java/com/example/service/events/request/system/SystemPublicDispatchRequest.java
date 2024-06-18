package com.example.service.events.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicDispatchRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_dispatch:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_dispatch:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_dispatch:request:create";
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.util.UUID> subscriptionId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> statusId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_dispatch:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.util.UUID> subscriptionId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> statusId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_dispatch:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.util.UUID> subscriptionId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> statusId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
        
        private Optional<com.example.service.events.request.system.SystemPublicSubscriptionRequest.Select> subscription;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicDispatchRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_dispatch:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.util.UUID> subscriptionId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.Integer> statusId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
}
