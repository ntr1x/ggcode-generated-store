package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicAttachmentTypeModel;
import com.example.service.catalog.service.system.SystemPublicAttachmentTypeService;
import com.example.service.catalog.request.system.SystemPublicAttachmentTypeRequest;
import com.example.service.catalog.response.system.SystemPublicAttachmentTypeResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Dictionaries")
@RequestMapping("/system/public_attachment_type")
@RequiredArgsConstructor
public class SystemPublicAttachmentTypeController {

    private final SystemPublicAttachmentTypeService systemPublicAttachmentTypeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicAttachmentTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicAttachmentTypeRequest.Context context,
            @RequestBody @Valid SystemPublicAttachmentTypeRequest.Create request
    ) {
        return systemPublicAttachmentTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicAttachmentTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicAttachmentTypeRequest.Context context,
            @RequestBody @Valid SystemPublicAttachmentTypeRequest.Id key
    ) {
        return systemPublicAttachmentTypeService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicAttachmentTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicAttachmentTypeRequest.Context context,
            @RequestBody SystemPublicAttachmentTypeRequest.Update request
    ) {
        return systemPublicAttachmentTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicAttachmentTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicAttachmentTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicAttachmentTypeRequest.Id recordKey = SystemPublicAttachmentTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicAttachmentTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicAttachmentTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicAttachmentTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody @Valid SystemPublicAttachmentTypeRequest.Replace request
    ) {
        SystemPublicAttachmentTypeRequest.Id recordKey = SystemPublicAttachmentTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicAttachmentTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicAttachmentTypeModel> select(
            @Parameter(hidden = true) SystemPublicAttachmentTypeRequest.Context context,
            @RequestBody @Valid SystemPublicAttachmentTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicAttachmentTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicAttachmentTypeModel get(
            @Parameter(hidden = true) SystemPublicAttachmentTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicAttachmentTypeRequest.Id recordKey = SystemPublicAttachmentTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicAttachmentTypeService.get(context, recordKey);
    }
}
