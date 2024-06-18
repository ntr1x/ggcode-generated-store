package com.example.service.events.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicEventTypeInfoRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_event_type_info:id";
        
        private java.lang.String name;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_event_type_info:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_event_type_info:request:create";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_event_type_info:request:replace";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_event_type_info:request:select";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicEventTypeInfoRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_event_type_info:request:update";
        
        private java.lang.String name;
        
        private Optional<java.lang.String> description;
    }
}
