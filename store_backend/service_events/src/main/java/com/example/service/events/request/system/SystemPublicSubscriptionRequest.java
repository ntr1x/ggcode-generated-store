package com.example.service.events.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicSubscriptionRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_subscription:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_subscription:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_subscription:request:create";
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_subscription:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_subscription:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicSubscriptionRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_subscription:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.Integer> typeId;
        
        private Optional<java.lang.String> sessionId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<com.fasterxml.jackson.databind.JsonNode> payload;
    }
}
