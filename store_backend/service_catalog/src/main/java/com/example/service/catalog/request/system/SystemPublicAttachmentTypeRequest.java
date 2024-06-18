package com.example.service.catalog.request.system;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

public interface SystemPublicAttachmentTypeRequest {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeRequestId")
    class Id {
        @Hidden @Builder.Default
        private String __name = "system_public_attachment_type:id";
        
        private java.lang.Integer id;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeRequestContext")
    class Context {
        @Hidden @Builder.Default
        private String __name = "system_public_attachment_type:context";
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeRequestCreate")
    class Create {
        @Hidden @Builder.Default
        private String __name = "system_public_attachment_type:request:create";
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeRequestPut")
    class Replace {
        @Hidden @Builder.Default
        private String __name = "system_public_attachment_type:request:replace";
        
        private Optional<java.lang.Integer> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeRequestSelect")
    class Select {
        @Hidden @Builder.Default
        private String __name = "system_public_attachment_type:request:select";
        
        private Optional<java.lang.Integer> id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
    
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "SystemPublicAttachmentTypeRequestUpdate")
    class Update {
        @Hidden @Builder.Default
        private String __name = "system_public_attachment_type:request:update";
        
        private java.lang.Integer id;
        
        private Optional<java.lang.String> name;
        
        private Optional<java.lang.String> description;
    }
}
