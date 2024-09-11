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

public interface SystemPublicGuideRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_guide:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_guide:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_guide:request:create";
        
        private Optional<java.util.UUID> subjectId;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> link;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> attachment;
        
        private Optional<java.lang.Integer> position;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_guide:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> subjectId;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> link;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> attachment;
        
        private Optional<java.lang.Integer> position;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicGuideRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_guide:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> subjectId;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> link;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> attachment;
        
        private Optional<java.lang.Integer> position;
        
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
    @Schema(name = "SystemPublicGuideRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_guide:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.util.UUID> subjectId;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> link;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> attachment;
        
        private Optional<java.lang.Integer> position;
        
        private Optional<java.time.LocalDateTime> createdAt;
        
        private Optional<java.time.LocalDateTime> updatedAt;
    }
}
