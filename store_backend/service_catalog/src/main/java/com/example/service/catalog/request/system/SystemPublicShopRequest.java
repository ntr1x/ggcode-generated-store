package com.example.service.catalog.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicShopRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_shop:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_shop:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_shop:request:create";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.math.BigDecimal> lon;
        
        private Optional<java.math.BigDecimal> lat;
        
        private Optional<java.util.UUID> regionId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_shop:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.math.BigDecimal> lon;
        
        private Optional<java.math.BigDecimal> lat;
        
        private Optional<java.util.UUID> regionId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_shop:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.math.BigDecimal> lon;
        
        private Optional<java.math.BigDecimal> lat;
        
        private Optional<java.util.UUID> regionId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicShopRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_shop:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.math.BigDecimal> lon;
        
        private Optional<java.math.BigDecimal> lat;
        
        private Optional<java.util.UUID> regionId;
    }
}
