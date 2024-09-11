package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicPromotionModel;
import com.example.service.catalog.service.system.SystemPublicPromotionService;
import com.example.service.catalog.request.system.SystemPublicPromotionRequest;
import com.example.service.catalog.response.system.SystemPublicPromotionResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Promotions")
@RequestMapping("/system/public_promotion")
@RequiredArgsConstructor
public class SystemPublicPromotionController {

    private final SystemPublicPromotionService systemPublicPromotionService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicPromotionResponse.Create create(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @RequestBody @Valid SystemPublicPromotionRequest.Create request
    ) {
        return systemPublicPromotionService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPromotionResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @RequestBody @Valid SystemPublicPromotionRequest.Id key
    ) {
        return systemPublicPromotionService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPromotionResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicPromotionRequest.Id> keys
    ) {
        return systemPublicPromotionService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicPromotionResponse.Update update(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @RequestBody SystemPublicPromotionRequest.Update request
    ) {
        return systemPublicPromotionService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPromotionResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicPromotionRequest.Id recordKey = SystemPublicPromotionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicPromotionResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicPromotionRequest.Replace request
    ) {
        SystemPublicPromotionRequest.Id recordKey = SystemPublicPromotionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicPromotionModel> select(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @RequestBody @Valid SystemPublicPromotionRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicPromotionService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicPromotionModel get(
            @Parameter(hidden = true) SystemPublicPromotionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicPromotionRequest.Id recordKey = SystemPublicPromotionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionService.get(context, recordKey);
    }
}
