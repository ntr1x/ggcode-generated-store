package com.example.service.payments.controller.profile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.model.profile.ProfilePublicBasketEntryModel;
import com.example.service.payments.service.profile.ProfilePublicBasketEntryService;
import com.example.service.payments.request.profile.ProfilePublicBasketEntryRequest;
import com.example.service.payments.response.profile.ProfilePublicBasketEntryResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Basket")
@RequestMapping("/profile/public_basket_entry")
@RequiredArgsConstructor
public class ProfilePublicBasketEntryController {

    private final ProfilePublicBasketEntryService profilePublicBasketEntryService;
    
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public ProfilePublicBasketEntryResponse.Create create(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @RequestBody @Valid ProfilePublicBasketEntryRequest.Create request
    ) {
        return profilePublicBasketEntryService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicBasketEntryResponse.Remove remove(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @RequestBody @Valid ProfilePublicBasketEntryRequest.Id key
    ) {
        return profilePublicBasketEntryService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicBasketEntryResponse.RemoveAll removeAll(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @RequestBody @Valid Collection<ProfilePublicBasketEntryRequest.Id> keys
    ) {
        return profilePublicBasketEntryService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public ProfilePublicBasketEntryResponse.Update update(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @RequestBody ProfilePublicBasketEntryRequest.Update request
    ) {
        return profilePublicBasketEntryService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public ProfilePublicBasketEntryResponse.Remove remove(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        ProfilePublicBasketEntryRequest.Id recordKey = ProfilePublicBasketEntryRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicBasketEntryService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public ProfilePublicBasketEntryResponse.Replace replace(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid ProfilePublicBasketEntryRequest.Replace request
    ) {
        ProfilePublicBasketEntryRequest.Id recordKey = ProfilePublicBasketEntryRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicBasketEntryService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<ProfilePublicBasketEntryModel> select(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @RequestBody @Valid ProfilePublicBasketEntryRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return profilePublicBasketEntryService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public ProfilePublicBasketEntryModel get(
            @Parameter(hidden = true) ProfilePublicBasketEntryRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        ProfilePublicBasketEntryRequest.Id recordKey = ProfilePublicBasketEntryRequest.Id.builder()
                .id(id)
                .build();
        return profilePublicBasketEntryService.get(context, recordKey);
    }
}
