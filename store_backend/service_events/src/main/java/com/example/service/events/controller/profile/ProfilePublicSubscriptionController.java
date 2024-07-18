package com.example.service.events.controller.profile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.profile.ProfilePublicSubscriptionModel;
import com.example.service.events.service.profile.ProfilePublicSubscriptionService;
import com.example.service.events.request.profile.ProfilePublicSubscriptionRequest;
import com.example.service.events.response.profile.ProfilePublicSubscriptionResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Subscriptions")
@RequestMapping("/profile/public_subscription")
@RequiredArgsConstructor
public class ProfilePublicSubscriptionController {

    private final ProfilePublicSubscriptionService profilePublicSubscriptionService;
    
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public ProfilePublicSubscriptionResponse.Create create(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @RequestBody @Valid ProfilePublicSubscriptionRequest.Create request
    ) {
        return profilePublicSubscriptionService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicSubscriptionResponse.Remove remove(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @RequestBody @Valid ProfilePublicSubscriptionRequest.Id key
    ) {
        return profilePublicSubscriptionService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicSubscriptionResponse.RemoveAll removeAll(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @RequestBody @Valid Collection<ProfilePublicSubscriptionRequest.Id> keys
    ) {
        return profilePublicSubscriptionService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public ProfilePublicSubscriptionResponse.Update update(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @RequestBody ProfilePublicSubscriptionRequest.Update request
    ) {
        return profilePublicSubscriptionService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicSubscriptionResponse.Remove remove(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        ProfilePublicSubscriptionRequest.Id recordKey = ProfilePublicSubscriptionRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicSubscriptionService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public ProfilePublicSubscriptionResponse.Replace replace(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid ProfilePublicSubscriptionRequest.Replace request
    ) {
        ProfilePublicSubscriptionRequest.Id recordKey = ProfilePublicSubscriptionRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicSubscriptionService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<ProfilePublicSubscriptionModel> select(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @RequestBody @Valid ProfilePublicSubscriptionRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return profilePublicSubscriptionService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public ProfilePublicSubscriptionModel get(
            @Parameter(hidden = true) ProfilePublicSubscriptionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        ProfilePublicSubscriptionRequest.Id recordKey = ProfilePublicSubscriptionRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicSubscriptionService.get(context, recordKey);
    }
}
