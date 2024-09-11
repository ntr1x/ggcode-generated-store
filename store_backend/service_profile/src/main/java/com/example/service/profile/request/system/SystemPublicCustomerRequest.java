package com.example.service.profile.request.system;

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

public interface SystemPublicCustomerRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_customer:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_customer:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_customer:request:create";
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> surname;
        
        private Optional<java.lang.String> patronymic;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_customer:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> surname;
        
        private Optional<java.lang.String> patronymic;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicCustomerRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_customer:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> surname;
        
        private Optional<java.lang.String> patronymic;

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
    @Schema(name = "SystemPublicCustomerRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_customer:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> surname;
        
        private Optional<java.lang.String> patronymic;
    }
}
