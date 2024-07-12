package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicTemplateTypeModel;
import com.example.service.events.service.system.SystemPublicTemplateTypeService;
import com.example.service.events.request.system.SystemPublicTemplateTypeRequest;
import com.example.service.events.response.system.SystemPublicTemplateTypeResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Template Types")
@RequestMapping("/system/public_template_type")
@RequiredArgsConstructor
public class SystemPublicTemplateTypeController {

    private final SystemPublicTemplateTypeService systemPublicTemplateTypeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicTemplateTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicTemplateTypeRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateTypeRequest.Create request
    ) {
        return systemPublicTemplateTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicTemplateTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicTemplateTypeRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateTypeRequest.Id key
    ) {
        return systemPublicTemplateTypeService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicTemplateTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicTemplateTypeRequest.Context context,
            @RequestBody SystemPublicTemplateTypeRequest.Update request
    ) {
        return systemPublicTemplateTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicTemplateTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicTemplateTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicTemplateTypeRequest.Id recordKey = SystemPublicTemplateTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicTemplateTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicTemplateTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody @Valid SystemPublicTemplateTypeRequest.Replace request
    ) {
        SystemPublicTemplateTypeRequest.Id recordKey = SystemPublicTemplateTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicTemplateTypeModel> select(
            @Parameter(hidden = true) SystemPublicTemplateTypeRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicTemplateTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicTemplateTypeModel get(
            @Parameter(hidden = true) SystemPublicTemplateTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicTemplateTypeRequest.Id recordKey = SystemPublicTemplateTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateTypeService.get(context, recordKey);
    }
}
