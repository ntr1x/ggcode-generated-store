package com.example.service.catalog.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicPromotionTargetRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion_target:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion_target:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion_target:request:create";
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> promotionId;
        
        private Optional<java.util.UUID> categoryId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion_target:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> promotionId;
        
        private Optional<java.util.UUID> categoryId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion_target:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> promotionId;
        
        private Optional<java.util.UUID> categoryId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPromotionTargetRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_promotion_target:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.Integer> sourceTypeId;
        
        private Optional<java.util.UUID> promotionId;
        
        private Optional<java.util.UUID> categoryId;
        
        private Optional<java.util.UUID> productId;
        
        private Optional<java.util.UUID> regionId;
        
        private Optional<java.util.UUID> shopId;
    }
}
