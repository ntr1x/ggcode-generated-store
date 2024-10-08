package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicCategoryModel;
import com.example.service.catalog.service.system.SystemPublicCategoryService;
import com.example.service.catalog.request.system.SystemPublicCategoryRequest;
import com.example.service.catalog.response.system.SystemPublicCategoryResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Product Categories")
@RequestMapping("/system/public_category")
@RequiredArgsConstructor
public class SystemPublicCategoryController {

    private final SystemPublicCategoryService systemPublicCategoryService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicCategoryResponse.Create create(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @RequestBody @Valid SystemPublicCategoryRequest.Create request
    ) {
        return systemPublicCategoryService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicCategoryResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @RequestBody @Valid SystemPublicCategoryRequest.Id key
    ) {
        return systemPublicCategoryService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicCategoryResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicCategoryRequest.Id> keys
    ) {
        return systemPublicCategoryService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicCategoryResponse.Update update(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @RequestBody SystemPublicCategoryRequest.Update request
    ) {
        return systemPublicCategoryService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicCategoryResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicCategoryRequest.Id recordKey = SystemPublicCategoryRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicCategoryService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicCategoryResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicCategoryRequest.Replace request
    ) {
        SystemPublicCategoryRequest.Id recordKey = SystemPublicCategoryRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicCategoryService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicCategoryModel> select(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @RequestBody @Valid SystemPublicCategoryRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicCategoryService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicCategoryModel get(
            @Parameter(hidden = true) SystemPublicCategoryRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicCategoryRequest.Id recordKey = SystemPublicCategoryRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicCategoryService.get(context, recordKey);
    }
}
