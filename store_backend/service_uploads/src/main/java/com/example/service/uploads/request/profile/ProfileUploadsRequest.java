package com.example.service.uploads.request.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProfileUploadsRequest {

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfileUploadsRequestPreSign")
    class PreSignUpload {
        @NotEmpty
        private String bucket;
        @NotEmpty
        private String key;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfileUploadsRequestUpload")
    class UploadFile {
        @NotEmpty
        private String bucket;
        @NotEmpty
        private String object;
        private Map<String, String> tags;
        @NotEmpty
        private MultipartFile file;
    }
}
