package com.example.service.structure.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.model.system.SystemPublicNetworkModel;
import com.example.service.structure.service.system.SystemPublicNetworkService;
import com.example.service.structure.request.system.SystemPublicNetworkRequest;
import com.example.service.structure.response.system.SystemPublicNetworkResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Networks")
@RequestMapping("/system/public_network")
@RequiredArgsConstructor
public class SystemPublicNetworkController {

    private final SystemPublicNetworkService systemPublicNetworkService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicNetworkResponse.Create create(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @RequestBody @Valid SystemPublicNetworkRequest.Create request
    ) {
        return systemPublicNetworkService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicNetworkResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @RequestBody @Valid SystemPublicNetworkRequest.Id key
    ) {
        return systemPublicNetworkService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicNetworkResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicNetworkRequest.Id> keys
    ) {
        return systemPublicNetworkService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicNetworkResponse.Update update(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @RequestBody SystemPublicNetworkRequest.Update request
    ) {
        return systemPublicNetworkService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicNetworkResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicNetworkRequest.Id recordKey = SystemPublicNetworkRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicNetworkService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicNetworkResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicNetworkRequest.Replace request
    ) {
        SystemPublicNetworkRequest.Id recordKey = SystemPublicNetworkRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicNetworkService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicNetworkModel> select(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @RequestBody @Valid SystemPublicNetworkRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicNetworkService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicNetworkModel get(
            @Parameter(hidden = true) SystemPublicNetworkRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicNetworkRequest.Id recordKey = SystemPublicNetworkRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicNetworkService.get(context, recordKey);
    }
}
