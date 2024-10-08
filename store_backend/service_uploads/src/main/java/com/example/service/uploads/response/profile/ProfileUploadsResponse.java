package com.example.service.uploads.response.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

public interface ProfileUploadsResponse {
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfileUploadsResponsePreSignUpload")
    class PreSignUpload {
        private Map<String, String> formData;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ProfileS3ResponsePreSignUpload")
    class UploadFile {
        private String bucket;
        private String region;
        private String object;
        private String etag;
        private String versionId;
    }
}
