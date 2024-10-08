package com.example.service.uploads.controller.profile;

import com.example.service.uploads.request.profile.ProfileUploadsRequest;
import com.example.service.uploads.response.profile.ProfileUploadsResponse;
import com.example.service.uploads.service.profile.ProfileUploadsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.UUID;

@RestController
@Tag(name = "Profile Uploads")
@RequestMapping("/profile/uploads")
@RequiredArgsConstructor
public class ProfileUploadsController {

    private final ProfileUploadsService profileUploadsService;

    @PostMapping("/preSign")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Pre-sign upload data")
    public ProfileUploadsResponse.PreSignUpload preSign(@RequestBody @Valid ProfileUploadsRequest.PreSignUpload request) {
        return profileUploadsService.preSignUpload(request);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Upload")
    public ProfileUploadsResponse.UploadFile upload(
            @RequestParam("id") @NotNull UUID id,
            @RequestParam("bucket") @NotEmpty String bucket,
            @RequestPart("file") @NotNull MultipartFile file,
            Principal principal
    ) {

        HashMap<String, String> tags = new HashMap<>();
        tags.put("principal", principal.getName());
        tags.put("filename", file.getOriginalFilename());

        ProfileUploadsRequest.UploadFile request = ProfileUploadsRequest.UploadFile
                .builder()
                .bucket(bucket)
                .object(id == null ? UUID.randomUUID().toString() : id.toString())
                .tags(tags)
                .file(file)
                .build();

        return profileUploadsService.upload(request);
    }
}
