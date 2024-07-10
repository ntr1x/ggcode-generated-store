package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicShopModel;
import com.example.service.catalog.service.system.SystemPublicShopService;
import com.example.service.catalog.request.system.SystemPublicShopRequest;
import com.example.service.catalog.response.system.SystemPublicShopResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Shops")
@RequestMapping("/system/public_shop")
@RequiredArgsConstructor
public class SystemPublicShopController {

    private final SystemPublicShopService systemPublicShopService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicShopResponse.Create create(
            @Parameter(hidden = true) SystemPublicShopRequest.Context context,
            @RequestBody @Valid SystemPublicShopRequest.Create request
    ) {
        return systemPublicShopService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicShopResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicShopRequest.Context context,
            @RequestBody @Valid SystemPublicShopRequest.Id key
    ) {
        return systemPublicShopService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicShopResponse.Update update(
            @Parameter(hidden = true) SystemPublicShopRequest.Context context,
            @RequestBody SystemPublicShopRequest.Update request
    ) {
        return systemPublicShopService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicShopResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicShopRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicShopRequest.Id recordKey = SystemPublicShopRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicShopService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicShopResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicShopRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicShopRequest.Replace request
    ) {
        SystemPublicShopRequest.Id recordKey = SystemPublicShopRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicShopService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicShopModel> select(
            @Parameter(hidden = true) SystemPublicShopRequest.Context context,
            @RequestBody @Valid SystemPublicShopRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicShopService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicShopModel get(
            @Parameter(hidden = true) SystemPublicShopRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicShopRequest.Id recordKey = SystemPublicShopRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicShopService.get(context, recordKey);
    }
}
