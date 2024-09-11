package com.example.service.catalog.request.system;

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

public interface SystemPublicImageTypeRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_image_type:id";
        
        private java.lang.Integer id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_image_type:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_image_type:request:create";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> format;
        
        private Optional<java.lang.Integer> width;
        
        private Optional<java.lang.Integer> height;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_image_type:request:replace";
        
        private Optional<java.lang.Integer> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> format;
        
        private Optional<java.lang.Integer> width;
        
        private Optional<java.lang.Integer> height;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicImageTypeRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_image_type:request:select";
        
        private Optional<java.lang.Integer> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> format;
        
        private Optional<java.lang.Integer> width;
        
        private Optional<java.lang.Integer> height;

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
    @Schema(name = "SystemPublicImageTypeRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_image_type:request:update";
        
        private java.lang.Integer id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
        
        private Optional<java.lang.String> format;
        
        private Optional<java.lang.Integer> width;
        
        private Optional<java.lang.Integer> height;
    }
}
