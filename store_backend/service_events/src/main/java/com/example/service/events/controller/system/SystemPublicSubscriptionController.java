package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicSubscriptionModel;
import com.example.service.events.service.system.SystemPublicSubscriptionService;
import com.example.service.events.request.system.SystemPublicSubscriptionRequest;
import com.example.service.events.response.system.SystemPublicSubscriptionResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Subscriptions")
@RequestMapping("/system/public_subscription")
@RequiredArgsConstructor
public class SystemPublicSubscriptionController {

    private final SystemPublicSubscriptionService systemPublicSubscriptionService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicSubscriptionResponse.Create create(
            @Parameter(hidden = true) SystemPublicSubscriptionRequest.Context context,
            @RequestBody SystemPublicSubscriptionRequest.Create request
    ) {
        return systemPublicSubscriptionService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicSubscriptionResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicSubscriptionRequest.Context context,
            @RequestBody SystemPublicSubscriptionRequest.Id key
    ) {
        return systemPublicSubscriptionService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicSubscriptionResponse.Update update(
            @Parameter(hidden = true) SystemPublicSubscriptionRequest.Context context,
            @RequestBody SystemPublicSubscriptionRequest.Update request
    ) {
        return systemPublicSubscriptionService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicSubscriptionResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicSubscriptionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicSubscriptionRequest.Id recordKey = SystemPublicSubscriptionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSubscriptionService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicSubscriptionResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicSubscriptionRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody SystemPublicSubscriptionRequest.Replace request
    ) {
        SystemPublicSubscriptionRequest.Id recordKey = SystemPublicSubscriptionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSubscriptionService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicSubscriptionModel> select(
            @Parameter(hidden = true) SystemPublicSubscriptionRequest.Context context,
            @RequestBody SystemPublicSubscriptionRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicSubscriptionService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicSubscriptionModel get(
            @Parameter(hidden = true) SystemPublicSubscriptionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicSubscriptionRequest.Id recordKey = SystemPublicSubscriptionRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicSubscriptionService.get(context, recordKey);
    }
}
