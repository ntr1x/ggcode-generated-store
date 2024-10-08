package com.example.service.uploads.service.profile;

import com.example.service.uploads.request.profile.ProfileUploadsRequest;
import com.example.service.uploads.response.profile.ProfileUploadsResponse;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PostPolicy;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ntr1x.common.minio.component.MinioBucketRegistry;
import org.ntr1x.common.minio.property.BucketProperty;
import org.ntr1x.common.minio.property.UploadPolicyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileUploadsService {

    private final MinioClient minioClient;
    private final MinioBucketRegistry minioBucketRegistry;

    public ProfileUploadsResponse.PreSignUpload preSignUpload(ProfileUploadsRequest.PreSignUpload request) {

        BucketProperty bucketProperty = minioBucketRegistry
                .bucket(request.getBucket())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown bucket"));

        PostPolicy policy = new PostPolicy(
                bucketProperty.getName(),
                ZonedDateTime.now().plus(bucketProperty.getUploadPolicy().getDuration()));

        policy.addEqualsCondition("key", request.getKey());

        Optional.ofNullable(bucketProperty.getUploadPolicy())
                .map(UploadPolicyProperty::getContentType)
                .ifPresent(v -> policy.addStartsWithCondition("Content-Type", v));

        Optional.ofNullable(bucketProperty.getUploadPolicy())
                .map(UploadPolicyProperty::getDataSize)
                .map(DataSize::toBytes)
                .ifPresent(v -> policy.addContentLengthRangeCondition(0, v));

        try {
            Map<String, String> formData = minioClient.getPresignedPostFormData(policy);

            return ProfileUploadsResponse.PreSignUpload
                    .builder()
                    .formData(formData)
                    .build();
        } catch (MinioException e) {
            log.warn("Cannot pre-sign upload", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        } catch (InvalidKeyException e) {
            log.warn("Cannot pre-sign upload", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        } catch (IOException e) {
            log.warn("Cannot pre-sign upload", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        } catch (NoSuchAlgorithmException e) {
            log.warn("Cannot pre-sign upload", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        }
    }

    public ProfileUploadsResponse.UploadFile upload(ProfileUploadsRequest.UploadFile request) {

        try {
            Path target = Files.createTempFile("log_", ".tmp");

            request.getFile().transferTo(target);

            UploadObjectArgs args = UploadObjectArgs
                    .builder()
                    .bucket(request.getBucket())
                    .object(request.getObject())
                    .filename(target.toAbsolutePath().toString())
                    .contentType(request.getFile().getContentType())
                    .tags(request.getTags())
                    .build();

            ObjectWriteResponse response = minioClient.uploadObject(args);

            return ProfileUploadsResponse.UploadFile
                    .builder()
                    .bucket(response.bucket())
                    .region(response.region())
                    .object(response.object())
                    .etag(response.etag())
                    .versionId(response.versionId())
                    .build();

        } catch (MinioException e) {
            log.warn("Cannot upload file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        } catch (InvalidKeyException e) {
            log.warn("Cannot upload file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        } catch (IOException e) {
            log.warn("Cannot upload file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        } catch (NoSuchAlgorithmException e) {
            log.warn("Cannot upload file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio request failed", e);
        }
    }
}
