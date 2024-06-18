package com.example.service.payments.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicOrderRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_order:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_order:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicOrderRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_order:request:create";
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> customerId;
        
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
    @Schema(name = "SystemPublicOrderRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_order:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> customerId;
        
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
    @Schema(name = "SystemPublicOrderRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_order:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> customerId;
        
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
    @Schema(name = "SystemPublicOrderRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_order:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> customerId;
        
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
}
