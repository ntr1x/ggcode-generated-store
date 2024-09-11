package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicTemplateModel;
import com.example.service.events.service.system.SystemPublicTemplateService;
import com.example.service.events.request.system.SystemPublicTemplateRequest;
import com.example.service.events.response.system.SystemPublicTemplateResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Templates")
@RequestMapping("/system/public_template")
@RequiredArgsConstructor
public class SystemPublicTemplateController {

    private final SystemPublicTemplateService systemPublicTemplateService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicTemplateResponse.Create create(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateRequest.Create request
    ) {
        return systemPublicTemplateService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicTemplateResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateRequest.Id key
    ) {
        return systemPublicTemplateService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicTemplateResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicTemplateRequest.Id> keys
    ) {
        return systemPublicTemplateService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicTemplateResponse.Update update(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @RequestBody SystemPublicTemplateRequest.Update request
    ) {
        return systemPublicTemplateService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicTemplateResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicTemplateRequest.Id recordKey = SystemPublicTemplateRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicTemplateResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicTemplateRequest.Replace request
    ) {
        SystemPublicTemplateRequest.Id recordKey = SystemPublicTemplateRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicTemplateModel> select(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicTemplateService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicTemplateModel get(
            @Parameter(hidden = true) SystemPublicTemplateRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicTemplateRequest.Id recordKey = SystemPublicTemplateRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateService.get(context, recordKey);
    }
}
