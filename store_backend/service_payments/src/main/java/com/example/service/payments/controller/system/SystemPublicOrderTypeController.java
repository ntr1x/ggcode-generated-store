package com.example.service.payments.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.model.system.SystemPublicOrderTypeModel;
import com.example.service.payments.service.system.SystemPublicOrderTypeService;
import com.example.service.payments.request.system.SystemPublicOrderTypeRequest;
import com.example.service.payments.response.system.SystemPublicOrderTypeResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Dictionaries")
@RequestMapping("/system/public_order_type")
@RequiredArgsConstructor
public class SystemPublicOrderTypeController {

    private final SystemPublicOrderTypeService systemPublicOrderTypeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicOrderTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @RequestBody @Valid SystemPublicOrderTypeRequest.Create request
    ) {
        return systemPublicOrderTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicOrderTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @RequestBody @Valid SystemPublicOrderTypeRequest.Id key
    ) {
        return systemPublicOrderTypeService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicOrderTypeResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicOrderTypeRequest.Id> keys
    ) {
        return systemPublicOrderTypeService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicOrderTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @RequestBody SystemPublicOrderTypeRequest.Update request
    ) {
        return systemPublicOrderTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicOrderTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicOrderTypeRequest.Id recordKey = SystemPublicOrderTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicOrderTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicOrderTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody @Valid SystemPublicOrderTypeRequest.Replace request
    ) {
        SystemPublicOrderTypeRequest.Id recordKey = SystemPublicOrderTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicOrderTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicOrderTypeModel> select(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @RequestBody @Valid SystemPublicOrderTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicOrderTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicOrderTypeModel get(
            @Parameter(hidden = true) SystemPublicOrderTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicOrderTypeRequest.Id recordKey = SystemPublicOrderTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicOrderTypeService.get(context, recordKey);
    }
}
