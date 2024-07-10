package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicRegionModel;
import com.example.service.catalog.service.system.SystemPublicRegionService;
import com.example.service.catalog.request.system.SystemPublicRegionRequest;
import com.example.service.catalog.response.system.SystemPublicRegionResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Regions")
@RequestMapping("/system/public_region")
@RequiredArgsConstructor
public class SystemPublicRegionController {

    private final SystemPublicRegionService systemPublicRegionService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicRegionResponse.Create create(
            @Parameter(hidden = true) SystemPublicRegionRequest.Context context,
            @RequestBody @Valid SystemPublicRegionRequest.Create request
    ) {
        return systemPublicRegionService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicRegionResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicRegionRequest.Context context,
            @RequestBody @Valid SystemPublicRegionRequest.Id key
    ) {
        return systemPublicRegionService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicRegionResponse.Update update(
            @Parameter(hidden = true) SystemPublicRegionRequest.Context context,
            @RequestBody SystemPublicRegionRequest.Update request
    ) {
        return systemPublicRegionService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicRegionResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicRegionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicRegionRequest.Id recordKey = SystemPublicRegionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicRegionService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicRegionResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicRegionRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicRegionRequest.Replace request
    ) {
        SystemPublicRegionRequest.Id recordKey = SystemPublicRegionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicRegionService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicRegionModel> select(
            @Parameter(hidden = true) SystemPublicRegionRequest.Context context,
            @RequestBody @Valid SystemPublicRegionRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicRegionService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicRegionModel get(
            @Parameter(hidden = true) SystemPublicRegionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicRegionRequest.Id recordKey = SystemPublicRegionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicRegionService.get(context, recordKey);
    }
}
