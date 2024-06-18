package com.example.service.catalog.request.anonymous;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface AnonymousPublicShopRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "anonymous_public_shop:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "anonymous_public_shop:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "AnonymousPublicShopRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "anonymous_public_shop:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.math.BigDecimal> lon;
        
        private Optional<java.math.BigDecimal> lat;
        
        private Optional<java.util.UUID> regionId;
    }
}
