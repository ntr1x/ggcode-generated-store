package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicPromotionTypeModel;
import com.example.service.catalog.service.system.SystemPublicPromotionTypeService;
import com.example.service.catalog.request.system.SystemPublicPromotionTypeRequest;
import com.example.service.catalog.response.system.SystemPublicPromotionTypeResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Dictionaries")
@RequestMapping("/system/public_promotion_type")
@RequiredArgsConstructor
public class SystemPublicPromotionTypeController {

    private final SystemPublicPromotionTypeService systemPublicPromotionTypeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicPromotionTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicPromotionTypeRequest.Context context,
            @RequestBody @Valid SystemPublicPromotionTypeRequest.Create request
    ) {
        return systemPublicPromotionTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPromotionTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPromotionTypeRequest.Context context,
            @RequestBody @Valid SystemPublicPromotionTypeRequest.Id key
    ) {
        return systemPublicPromotionTypeService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicPromotionTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicPromotionTypeRequest.Context context,
            @RequestBody SystemPublicPromotionTypeRequest.Update request
    ) {
        return systemPublicPromotionTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPromotionTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPromotionTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicPromotionTypeRequest.Id recordKey = SystemPublicPromotionTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicPromotionTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicPromotionTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody @Valid SystemPublicPromotionTypeRequest.Replace request
    ) {
        SystemPublicPromotionTypeRequest.Id recordKey = SystemPublicPromotionTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicPromotionTypeModel> select(
            @Parameter(hidden = true) SystemPublicPromotionTypeRequest.Context context,
            @RequestBody @Valid SystemPublicPromotionTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicPromotionTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicPromotionTypeModel get(
            @Parameter(hidden = true) SystemPublicPromotionTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicPromotionTypeRequest.Id recordKey = SystemPublicPromotionTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionTypeService.get(context, recordKey);
    }
}
