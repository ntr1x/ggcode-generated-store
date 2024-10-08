package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicEventSourceInfoModel;
import com.example.service.events.service.system.SystemPublicEventSourceInfoService;
import com.example.service.events.request.system.SystemPublicEventSourceInfoRequest;
import com.example.service.events.response.system.SystemPublicEventSourceInfoResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Event Sources")
@RequestMapping("/system/public_event_source_info")
@RequiredArgsConstructor
public class SystemPublicEventSourceInfoController {

    private final SystemPublicEventSourceInfoService systemPublicEventSourceInfoService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicEventSourceInfoResponse.Create create(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @RequestBody @Valid SystemPublicEventSourceInfoRequest.Create request
    ) {
        return systemPublicEventSourceInfoService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicEventSourceInfoResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @RequestBody @Valid SystemPublicEventSourceInfoRequest.Id key
    ) {
        return systemPublicEventSourceInfoService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicEventSourceInfoResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicEventSourceInfoRequest.Id> keys
    ) {
        return systemPublicEventSourceInfoService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicEventSourceInfoResponse.Update update(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @RequestBody SystemPublicEventSourceInfoRequest.Update request
    ) {
        return systemPublicEventSourceInfoService.update(context, request);
    }
    
    @DeleteMapping("/i/{name}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicEventSourceInfoResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @PathVariable("name") java.lang.String name
    ) {
        SystemPublicEventSourceInfoRequest.Id recordKey = SystemPublicEventSourceInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventSourceInfoService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{name}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicEventSourceInfoResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @PathVariable("name") java.lang.String name,
            @RequestBody @Valid SystemPublicEventSourceInfoRequest.Replace request
    ) {
        SystemPublicEventSourceInfoRequest.Id recordKey = SystemPublicEventSourceInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventSourceInfoService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicEventSourceInfoModel> select(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @RequestBody @Valid SystemPublicEventSourceInfoRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicEventSourceInfoService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{name}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicEventSourceInfoModel get(
            @Parameter(hidden = true) SystemPublicEventSourceInfoRequest.Context context,
            @PathVariable("name") java.lang.String name
    ) {
        SystemPublicEventSourceInfoRequest.Id recordKey = SystemPublicEventSourceInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventSourceInfoService.get(context, recordKey);
    }
}
