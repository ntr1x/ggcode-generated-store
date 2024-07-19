package com.example.service.structure.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.model.system.SystemPublicGuideModel;
import com.example.service.structure.service.system.SystemPublicGuideService;
import com.example.service.structure.request.system.SystemPublicGuideRequest;
import com.example.service.structure.response.system.SystemPublicGuideResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Networks")
@RequestMapping("/system/public_guide")
@RequiredArgsConstructor
public class SystemPublicGuideController {

    private final SystemPublicGuideService systemPublicGuideService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicGuideResponse.Create create(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @RequestBody @Valid SystemPublicGuideRequest.Create request
    ) {
        return systemPublicGuideService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicGuideResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @RequestBody @Valid SystemPublicGuideRequest.Id key
    ) {
        return systemPublicGuideService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicGuideResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicGuideRequest.Id> keys
    ) {
        return systemPublicGuideService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicGuideResponse.Update update(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @RequestBody SystemPublicGuideRequest.Update request
    ) {
        return systemPublicGuideService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicGuideResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicGuideRequest.Id recordKey = SystemPublicGuideRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicGuideService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicGuideResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicGuideRequest.Replace request
    ) {
        SystemPublicGuideRequest.Id recordKey = SystemPublicGuideRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicGuideService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicGuideModel> select(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @RequestBody @Valid SystemPublicGuideRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicGuideService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicGuideModel get(
            @Parameter(hidden = true) SystemPublicGuideRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicGuideRequest.Id recordKey = SystemPublicGuideRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicGuideService.get(context, recordKey);
    }
}
