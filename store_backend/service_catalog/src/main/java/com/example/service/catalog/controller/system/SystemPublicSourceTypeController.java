package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicSourceTypeModel;
import com.example.service.catalog.service.system.SystemPublicSourceTypeService;
import com.example.service.catalog.request.system.SystemPublicSourceTypeRequest;
import com.example.service.catalog.response.system.SystemPublicSourceTypeResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Sources")
@RequestMapping("/system/public_source_type")
@RequiredArgsConstructor
public class SystemPublicSourceTypeController {

    private final SystemPublicSourceTypeService systemPublicSourceTypeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicSourceTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @RequestBody @Valid SystemPublicSourceTypeRequest.Create request
    ) {
        return systemPublicSourceTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicSourceTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @RequestBody @Valid SystemPublicSourceTypeRequest.Id key
    ) {
        return systemPublicSourceTypeService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicSourceTypeResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicSourceTypeRequest.Id> keys
    ) {
        return systemPublicSourceTypeService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicSourceTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @RequestBody SystemPublicSourceTypeRequest.Update request
    ) {
        return systemPublicSourceTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicSourceTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicSourceTypeRequest.Id recordKey = SystemPublicSourceTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSourceTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicSourceTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody @Valid SystemPublicSourceTypeRequest.Replace request
    ) {
        SystemPublicSourceTypeRequest.Id recordKey = SystemPublicSourceTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSourceTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicSourceTypeModel> select(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @RequestBody @Valid SystemPublicSourceTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicSourceTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicSourceTypeModel get(
            @Parameter(hidden = true) SystemPublicSourceTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicSourceTypeRequest.Id recordKey = SystemPublicSourceTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSourceTypeService.get(context, recordKey);
    }
}
