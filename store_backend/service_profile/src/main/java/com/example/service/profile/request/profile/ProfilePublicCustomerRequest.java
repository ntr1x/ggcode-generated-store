package com.example.service.profile.request.profile;

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

public interface ProfilePublicCustomerRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicCustomerRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "profile_public_customer:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicCustomerRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "profile_public_customer:context";
        
        private java.util.UUID customerId;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicCustomerRequestUpsert")
    class Upsert {
        @Hidden @Builder.Default
        private String __name = "profile_public_customer:request:upsert";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> surname;
        
        private Optional<java.lang.String> patronymic;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfilePublicCustomerRequestFind")
    class Find {
        @Hidden @Builder.Default
        private String __name = "profile_public_customer:request:find";
    }
}
