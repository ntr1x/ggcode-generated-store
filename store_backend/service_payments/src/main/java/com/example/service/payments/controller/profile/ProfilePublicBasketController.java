package com.example.service.payments.controller.profile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.model.profile.ProfilePublicBasketModel;
import com.example.service.payments.service.profile.ProfilePublicBasketService;
import com.example.service.payments.request.profile.ProfilePublicBasketRequest;
import com.example.service.payments.response.profile.ProfilePublicBasketResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Basket")
@RequestMapping("/profile/public_basket")
@RequiredArgsConstructor
public class ProfilePublicBasketController {

    private final ProfilePublicBasketService profilePublicBasketService;
    
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public ProfilePublicBasketResponse.Create create(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @RequestBody @Valid ProfilePublicBasketRequest.Create request
    ) {
        return profilePublicBasketService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicBasketResponse.Remove remove(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @RequestBody @Valid ProfilePublicBasketRequest.Id key
    ) {
        return profilePublicBasketService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicBasketResponse.RemoveAll removeAll(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @RequestBody @Valid Collection<ProfilePublicBasketRequest.Id> keys
    ) {
        return profilePublicBasketService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public ProfilePublicBasketResponse.Update update(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @RequestBody ProfilePublicBasketRequest.Update request
    ) {
        return profilePublicBasketService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicBasketResponse.Remove remove(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        ProfilePublicBasketRequest.Id recordKey = ProfilePublicBasketRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicBasketService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public ProfilePublicBasketResponse.Replace replace(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid ProfilePublicBasketRequest.Replace request
    ) {
        ProfilePublicBasketRequest.Id recordKey = ProfilePublicBasketRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicBasketService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<ProfilePublicBasketModel> select(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @RequestBody @Valid ProfilePublicBasketRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return profilePublicBasketService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public ProfilePublicBasketModel get(
            @Parameter(hidden = true) ProfilePublicBasketRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        ProfilePublicBasketRequest.Id recordKey = ProfilePublicBasketRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicBasketService.get(context, recordKey);
    }
}
