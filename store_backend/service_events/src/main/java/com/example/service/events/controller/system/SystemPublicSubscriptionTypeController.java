package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicSubscriptionTypeModel;
import com.example.service.events.service.system.SystemPublicSubscriptionTypeService;
import com.example.service.events.request.system.SystemPublicSubscriptionTypeRequest;
import com.example.service.events.response.system.SystemPublicSubscriptionTypeResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Subscription Types")
@RequestMapping("/system/public_subscription_type")
@RequiredArgsConstructor
public class SystemPublicSubscriptionTypeController {

    private final SystemPublicSubscriptionTypeService systemPublicSubscriptionTypeService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicSubscriptionTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicSubscriptionTypeRequest.Context context,
            @RequestBody SystemPublicSubscriptionTypeRequest.Create request
    ) {
        return systemPublicSubscriptionTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicSubscriptionTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicSubscriptionTypeRequest.Context context,
            @RequestBody SystemPublicSubscriptionTypeRequest.Id key
    ) {
        return systemPublicSubscriptionTypeService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicSubscriptionTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicSubscriptionTypeRequest.Context context,
            @RequestBody SystemPublicSubscriptionTypeRequest.Update request
    ) {
        return systemPublicSubscriptionTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicSubscriptionTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicSubscriptionTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicSubscriptionTypeRequest.Id recordKey = SystemPublicSubscriptionTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSubscriptionTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicSubscriptionTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicSubscriptionTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody SystemPublicSubscriptionTypeRequest.Replace request
    ) {
        SystemPublicSubscriptionTypeRequest.Id recordKey = SystemPublicSubscriptionTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSubscriptionTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicSubscriptionTypeModel> select(
            @Parameter(hidden = true) SystemPublicSubscriptionTypeRequest.Context context,
            @RequestBody SystemPublicSubscriptionTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicSubscriptionTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicSubscriptionTypeModel get(
            @Parameter(hidden = true) SystemPublicSubscriptionTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicSubscriptionTypeRequest.Id recordKey = SystemPublicSubscriptionTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSubscriptionTypeService.get(context, recordKey);
    }
}
