package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicItemModel;
import com.example.service.catalog.service.system.SystemPublicItemService;
import com.example.service.catalog.request.system.SystemPublicItemRequest;
import com.example.service.catalog.response.system.SystemPublicItemResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Product Items")
@RequestMapping("/system/public_item")
@RequiredArgsConstructor
public class SystemPublicItemController {

    private final SystemPublicItemService systemPublicItemService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicItemResponse.Create create(
            @Parameter(hidden = true) SystemPublicItemRequest.Context context,
            @RequestBody @Valid SystemPublicItemRequest.Create request
    ) {
        return systemPublicItemService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicItemResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicItemRequest.Context context,
            @RequestBody @Valid SystemPublicItemRequest.Id key
    ) {
        return systemPublicItemService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicItemResponse.Update update(
            @Parameter(hidden = true) SystemPublicItemRequest.Context context,
            @RequestBody SystemPublicItemRequest.Update request
    ) {
        return systemPublicItemService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicItemResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicItemRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicItemRequest.Id recordKey = SystemPublicItemRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicItemService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicItemResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicItemRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicItemRequest.Replace request
    ) {
        SystemPublicItemRequest.Id recordKey = SystemPublicItemRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicItemService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicItemModel> select(
            @Parameter(hidden = true) SystemPublicItemRequest.Context context,
            @RequestBody @Valid SystemPublicItemRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicItemService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicItemModel get(
            @Parameter(hidden = true) SystemPublicItemRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicItemRequest.Id recordKey = SystemPublicItemRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicItemService.get(context, recordKey);
    }
}
