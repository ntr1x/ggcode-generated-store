package com.example.service.catalog.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.system.SystemPublicPromotionTargetModel;
import com.example.service.catalog.service.system.SystemPublicPromotionTargetService;
import com.example.service.catalog.request.system.SystemPublicPromotionTargetRequest;
import com.example.service.catalog.response.system.SystemPublicPromotionTargetResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Promotion Targets")
@RequestMapping("/system/public_promotion_target")
@RequiredArgsConstructor
public class SystemPublicPromotionTargetController {

    private final SystemPublicPromotionTargetService systemPublicPromotionTargetService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicPromotionTargetResponse.Create create(
            @Parameter(hidden = true) SystemPublicPromotionTargetRequest.Context context,
            @RequestBody SystemPublicPromotionTargetRequest.Create request
    ) {
        return systemPublicPromotionTargetService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPromotionTargetResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPromotionTargetRequest.Context context,
            @RequestBody SystemPublicPromotionTargetRequest.Id key
    ) {
        return systemPublicPromotionTargetService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicPromotionTargetResponse.Update update(
            @Parameter(hidden = true) SystemPublicPromotionTargetRequest.Context context,
            @RequestBody SystemPublicPromotionTargetRequest.Update request
    ) {
        return systemPublicPromotionTargetService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPromotionTargetResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPromotionTargetRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicPromotionTargetRequest.Id recordKey = SystemPublicPromotionTargetRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionTargetService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicPromotionTargetResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicPromotionTargetRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody SystemPublicPromotionTargetRequest.Replace request
    ) {
        SystemPublicPromotionTargetRequest.Id recordKey = SystemPublicPromotionTargetRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionTargetService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicPromotionTargetModel> select(
            @Parameter(hidden = true) SystemPublicPromotionTargetRequest.Context context,
            @RequestBody SystemPublicPromotionTargetRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicPromotionTargetService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicPromotionTargetModel get(
            @Parameter(hidden = true) SystemPublicPromotionTargetRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicPromotionTargetRequest.Id recordKey = SystemPublicPromotionTargetRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPromotionTargetService.get(context, recordKey);
    }
}
