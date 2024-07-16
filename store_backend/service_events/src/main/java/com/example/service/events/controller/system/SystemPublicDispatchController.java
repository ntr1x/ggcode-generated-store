package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicDispatchModel;
import com.example.service.events.service.system.SystemPublicDispatchService;
import com.example.service.events.request.system.SystemPublicDispatchRequest;
import com.example.service.events.response.system.SystemPublicDispatchResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Dispatches")
@RequestMapping("/system/public_dispatch")
@RequiredArgsConstructor
public class SystemPublicDispatchController {

    private final SystemPublicDispatchService systemPublicDispatchService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicDispatchResponse.Create create(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @RequestBody @Valid SystemPublicDispatchRequest.Create request
    ) {
        return systemPublicDispatchService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicDispatchResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @RequestBody @Valid SystemPublicDispatchRequest.Id key
    ) {
        return systemPublicDispatchService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicDispatchResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicDispatchRequest.Id> keys
    ) {
        return systemPublicDispatchService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicDispatchResponse.Update update(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @RequestBody SystemPublicDispatchRequest.Update request
    ) {
        return systemPublicDispatchService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicDispatchResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicDispatchRequest.Id recordKey = SystemPublicDispatchRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicDispatchResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicDispatchRequest.Replace request
    ) {
        SystemPublicDispatchRequest.Id recordKey = SystemPublicDispatchRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicDispatchModel> select(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @RequestBody @Valid SystemPublicDispatchRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicDispatchService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicDispatchModel get(
            @Parameter(hidden = true) SystemPublicDispatchRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicDispatchRequest.Id recordKey = SystemPublicDispatchRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchService.get(context, recordKey);
    }
}
