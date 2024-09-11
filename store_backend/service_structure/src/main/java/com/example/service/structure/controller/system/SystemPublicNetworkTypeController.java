package com.example.service.structure.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.model.system.SystemPublicNetworkTypeModel;
import com.example.service.structure.service.system.SystemPublicNetworkTypeService;
import com.example.service.structure.request.system.SystemPublicNetworkTypeRequest;
import com.example.service.structure.response.system.SystemPublicNetworkTypeResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Network Types")
@RequestMapping("/system/public_network_type")
@RequiredArgsConstructor
public class SystemPublicNetworkTypeController {

    private final SystemPublicNetworkTypeService systemPublicNetworkTypeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicNetworkTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @RequestBody @Valid SystemPublicNetworkTypeRequest.Create request
    ) {
        return systemPublicNetworkTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicNetworkTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @RequestBody @Valid SystemPublicNetworkTypeRequest.Id key
    ) {
        return systemPublicNetworkTypeService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicNetworkTypeResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicNetworkTypeRequest.Id> keys
    ) {
        return systemPublicNetworkTypeService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicNetworkTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @RequestBody SystemPublicNetworkTypeRequest.Update request
    ) {
        return systemPublicNetworkTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicNetworkTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicNetworkTypeRequest.Id recordKey = SystemPublicNetworkTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicNetworkTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicNetworkTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody @Valid SystemPublicNetworkTypeRequest.Replace request
    ) {
        SystemPublicNetworkTypeRequest.Id recordKey = SystemPublicNetworkTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicNetworkTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicNetworkTypeModel> select(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @RequestBody @Valid SystemPublicNetworkTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicNetworkTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicNetworkTypeModel get(
            @Parameter(hidden = true) SystemPublicNetworkTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicNetworkTypeRequest.Id recordKey = SystemPublicNetworkTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicNetworkTypeService.get(context, recordKey);
    }
}
