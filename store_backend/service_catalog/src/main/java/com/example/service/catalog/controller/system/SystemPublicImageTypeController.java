package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicImageTypeModel;
import com.example.service.catalog.service.system.SystemPublicImageTypeService;
import com.example.service.catalog.request.system.SystemPublicImageTypeRequest;
import com.example.service.catalog.response.system.SystemPublicImageTypeResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Dictionaries")
@RequestMapping("/system/public_image_type")
@RequiredArgsConstructor
public class SystemPublicImageTypeController {

    private final SystemPublicImageTypeService systemPublicImageTypeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicImageTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicImageTypeRequest.Context context,
            @RequestBody SystemPublicImageTypeRequest.Create request
    ) {
        return systemPublicImageTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicImageTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicImageTypeRequest.Context context,
            @RequestBody SystemPublicImageTypeRequest.Id key
    ) {
        return systemPublicImageTypeService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicImageTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicImageTypeRequest.Context context,
            @RequestBody SystemPublicImageTypeRequest.Update request
    ) {
        return systemPublicImageTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicImageTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicImageTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicImageTypeRequest.Id recordKey = SystemPublicImageTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicImageTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicImageTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicImageTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody SystemPublicImageTypeRequest.Replace request
    ) {
        SystemPublicImageTypeRequest.Id recordKey = SystemPublicImageTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicImageTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicImageTypeModel> select(
            @Parameter(hidden = true) SystemPublicImageTypeRequest.Context context,
            @RequestBody SystemPublicImageTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicImageTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicImageTypeModel get(
            @Parameter(hidden = true) SystemPublicImageTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicImageTypeRequest.Id recordKey = SystemPublicImageTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicImageTypeService.get(context, recordKey);
    }
}
