package com.example.service.structure.request.system;

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

public interface SystemPublicNetworkRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_network:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_network:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_network:request:create";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> password;
        
        private Optional<java.lang.Boolean> hidden;
        
        private Optional<java.util.UUID> shopId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_network:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> password;
        
        private Optional<java.lang.Boolean> hidden;
        
        private Optional<java.util.UUID> shopId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicNetworkRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_network:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> password;
        
        private Optional<java.lang.Boolean> hidden;
        
        private Optional<java.util.UUID> shopId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;

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
    @Schema(name = "SystemPublicNetworkRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_network:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> password;
        
        private Optional<java.lang.Boolean> hidden;
        
        private Optional<java.util.UUID> shopId;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
    }
}
