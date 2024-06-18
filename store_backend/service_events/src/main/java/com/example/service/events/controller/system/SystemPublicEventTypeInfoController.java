package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicEventTypeInfoModel;
import com.example.service.events.service.system.SystemPublicEventTypeInfoService;
import com.example.service.events.request.system.SystemPublicEventTypeInfoRequest;
import com.example.service.events.response.system.SystemPublicEventTypeInfoResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Event Types")
@RequestMapping("/system/public_event_type_info")
@RequiredArgsConstructor
public class SystemPublicEventTypeInfoController {

    private final SystemPublicEventTypeInfoService systemPublicEventTypeInfoService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicEventTypeInfoResponse.Create create(
            @Parameter(hidden = true) SystemPublicEventTypeInfoRequest.Context context,
            @RequestBody SystemPublicEventTypeInfoRequest.Create request
    ) {
        return systemPublicEventTypeInfoService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicEventTypeInfoResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicEventTypeInfoRequest.Context context,
            @RequestBody SystemPublicEventTypeInfoRequest.Id key
    ) {
        return systemPublicEventTypeInfoService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicEventTypeInfoResponse.Update update(
            @Parameter(hidden = true) SystemPublicEventTypeInfoRequest.Context context,
            @RequestBody SystemPublicEventTypeInfoRequest.Update request
    ) {
        return systemPublicEventTypeInfoService.update(context, request);
    }
    
    @DeleteMapping("/i/{name}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicEventTypeInfoResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicEventTypeInfoRequest.Context context,
            @PathVariable("name") java.lang.String name
    ) {
        SystemPublicEventTypeInfoRequest.Id recordKey = SystemPublicEventTypeInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventTypeInfoService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{name}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicEventTypeInfoResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicEventTypeInfoRequest.Context context,
            @PathVariable("name") java.lang.String name,
            @RequestBody SystemPublicEventTypeInfoRequest.Replace request
    ) {
        SystemPublicEventTypeInfoRequest.Id recordKey = SystemPublicEventTypeInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventTypeInfoService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicEventTypeInfoModel> select(
            @Parameter(hidden = true) SystemPublicEventTypeInfoRequest.Context context,
            @RequestBody SystemPublicEventTypeInfoRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicEventTypeInfoService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{name}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicEventTypeInfoModel get(
            @Parameter(hidden = true) SystemPublicEventTypeInfoRequest.Context context,
            @PathVariable("name") java.lang.String name
    ) {
        SystemPublicEventTypeInfoRequest.Id recordKey = SystemPublicEventTypeInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventTypeInfoService.get(context, recordKey);
    }
}
