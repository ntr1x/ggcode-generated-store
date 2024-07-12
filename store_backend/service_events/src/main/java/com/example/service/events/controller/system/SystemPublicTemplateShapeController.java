package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicTemplateShapeModel;
import com.example.service.events.service.system.SystemPublicTemplateShapeService;
import com.example.service.events.request.system.SystemPublicTemplateShapeRequest;
import com.example.service.events.response.system.SystemPublicTemplateShapeResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Template Shapes")
@RequestMapping("/system/public_template_shape")
@RequiredArgsConstructor
public class SystemPublicTemplateShapeController {

    private final SystemPublicTemplateShapeService systemPublicTemplateShapeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicTemplateShapeResponse.Create create(
            @Parameter(hidden = true) SystemPublicTemplateShapeRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateShapeRequest.Create request
    ) {
        return systemPublicTemplateShapeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicTemplateShapeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicTemplateShapeRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateShapeRequest.Id key
    ) {
        return systemPublicTemplateShapeService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicTemplateShapeResponse.Update update(
            @Parameter(hidden = true) SystemPublicTemplateShapeRequest.Context context,
            @RequestBody SystemPublicTemplateShapeRequest.Update request
    ) {
        return systemPublicTemplateShapeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicTemplateShapeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicTemplateShapeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicTemplateShapeRequest.Id recordKey = SystemPublicTemplateShapeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateShapeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicTemplateShapeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicTemplateShapeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody @Valid SystemPublicTemplateShapeRequest.Replace request
    ) {
        SystemPublicTemplateShapeRequest.Id recordKey = SystemPublicTemplateShapeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateShapeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicTemplateShapeModel> select(
            @Parameter(hidden = true) SystemPublicTemplateShapeRequest.Context context,
            @RequestBody @Valid SystemPublicTemplateShapeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicTemplateShapeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicTemplateShapeModel get(
            @Parameter(hidden = true) SystemPublicTemplateShapeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicTemplateShapeRequest.Id recordKey = SystemPublicTemplateShapeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicTemplateShapeService.get(context, recordKey);
    }
}
