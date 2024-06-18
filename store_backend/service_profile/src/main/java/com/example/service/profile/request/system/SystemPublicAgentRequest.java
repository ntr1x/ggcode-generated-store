package com.example.service.profile.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicAgentRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_agent:id";
        
        private java.util.UUID id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_agent:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_agent:request:create";
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> kind;
        
        private Optional<java.lang.String> title;
        
        private Optional<java.lang.String> inn;
        
        private Optional<java.lang.String> kpp;
        
        private Optional<java.lang.String> bic;
        
        private Optional<java.lang.String> bank;
        
        private Optional<java.lang.String> okved;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> registrationAddress;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_agent:request:replace";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> kind;
        
        private Optional<java.lang.String> title;
        
        private Optional<java.lang.String> inn;
        
        private Optional<java.lang.String> kpp;
        
        private Optional<java.lang.String> bic;
        
        private Optional<java.lang.String> bank;
        
        private Optional<java.lang.String> okved;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> registrationAddress;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_agent:request:select";
        
        private Optional<java.util.UUID> id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> kind;
        
        private Optional<java.lang.String> title;
        
        private Optional<java.lang.String> inn;
        
        private Optional<java.lang.String> kpp;
        
        private Optional<java.lang.String> bic;
        
        private Optional<java.lang.String> bank;
        
        private Optional<java.lang.String> okved;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> registrationAddress;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAgentRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_agent:request:update";
        
        private java.util.UUID id;
        
        private Optional<java.util.UUID> customerId;
        
        private Optional<java.lang.String> kind;
        
        private Optional<java.lang.String> title;
        
        private Optional<java.lang.String> inn;
        
        private Optional<java.lang.String> kpp;
        
        private Optional<java.lang.String> bic;
        
        private Optional<java.lang.String> bank;
        
        private Optional<java.lang.String> okved;
        
        private Optional<java.lang.String> address;
        
        private Optional<java.lang.String> phone;
        
        private Optional<java.lang.String> email;
        
        private Optional<java.lang.String> registrationAddress;
    }
}
