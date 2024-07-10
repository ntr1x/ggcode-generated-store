package com.example.service.payments.request.system;

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

public interface SystemPublicPaymentStatusRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPaymentStatusRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_payment_status:id";
        
        private java.lang.Integer id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPaymentStatusRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_payment_status:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPaymentStatusRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_payment_status:request:create";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPaymentStatusRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_payment_status:request:replace";
        
        private Optional<java.lang.Integer> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicPaymentStatusRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_payment_status:request:select";
        
        private Optional<java.lang.Integer> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;

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
    @Schema(name = "SystemPublicPaymentStatusRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_payment_status:request:update";
        
        private java.lang.Integer id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
}
