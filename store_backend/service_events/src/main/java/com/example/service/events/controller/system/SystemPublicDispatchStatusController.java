package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicDispatchStatusModel;
import com.example.service.events.service.system.SystemPublicDispatchStatusService;
import com.example.service.events.request.system.SystemPublicDispatchStatusRequest;
import com.example.service.events.response.system.SystemPublicDispatchStatusResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Dispatch Statuses")
@RequestMapping("/system/public_dispatch_status")
@RequiredArgsConstructor
public class SystemPublicDispatchStatusController {

    private final SystemPublicDispatchStatusService systemPublicDispatchStatusService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicDispatchStatusResponse.Create create(
            @Parameter(hidden = true) SystemPublicDispatchStatusRequest.Context context,
            @RequestBody SystemPublicDispatchStatusRequest.Create request
    ) {
        return systemPublicDispatchStatusService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicDispatchStatusResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicDispatchStatusRequest.Context context,
            @RequestBody SystemPublicDispatchStatusRequest.Id key
    ) {
        return systemPublicDispatchStatusService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicDispatchStatusResponse.Update update(
            @Parameter(hidden = true) SystemPublicDispatchStatusRequest.Context context,
            @RequestBody SystemPublicDispatchStatusRequest.Update request
    ) {
        return systemPublicDispatchStatusService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicDispatchStatusResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicDispatchStatusRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicDispatchStatusRequest.Id recordKey = SystemPublicDispatchStatusRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchStatusService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicDispatchStatusResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicDispatchStatusRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody SystemPublicDispatchStatusRequest.Replace request
    ) {
        SystemPublicDispatchStatusRequest.Id recordKey = SystemPublicDispatchStatusRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchStatusService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicDispatchStatusModel> select(
            @Parameter(hidden = true) SystemPublicDispatchStatusRequest.Context context,
            @RequestBody SystemPublicDispatchStatusRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicDispatchStatusService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicDispatchStatusModel get(
            @Parameter(hidden = true) SystemPublicDispatchStatusRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicDispatchStatusRequest.Id recordKey = SystemPublicDispatchStatusRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchStatusService.get(context, recordKey);
    }
}
