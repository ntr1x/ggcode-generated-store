package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicProductModel;
import com.example.service.catalog.service.system.SystemPublicProductService;
import com.example.service.catalog.request.system.SystemPublicProductRequest;
import com.example.service.catalog.response.system.SystemPublicProductResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Products")
@RequestMapping("/system/public_product")
@RequiredArgsConstructor
public class SystemPublicProductController {

    private final SystemPublicProductService systemPublicProductService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicProductResponse.Create create(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @RequestBody @Valid SystemPublicProductRequest.Create request
    ) {
        return systemPublicProductService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicProductResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @RequestBody @Valid SystemPublicProductRequest.Id key
    ) {
        return systemPublicProductService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicProductResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicProductRequest.Id> keys
    ) {
        return systemPublicProductService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicProductResponse.Update update(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @RequestBody SystemPublicProductRequest.Update request
    ) {
        return systemPublicProductService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicProductResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicProductRequest.Id recordKey = SystemPublicProductRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicProductService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicProductResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicProductRequest.Replace request
    ) {
        SystemPublicProductRequest.Id recordKey = SystemPublicProductRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicProductService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicProductModel> select(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @RequestBody @Valid SystemPublicProductRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicProductService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicProductModel get(
            @Parameter(hidden = true) SystemPublicProductRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicProductRequest.Id recordKey = SystemPublicProductRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicProductService.get(context, recordKey);
    }
}
