package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicDispatchTypeModel;
import com.example.service.events.service.system.SystemPublicDispatchTypeService;
import com.example.service.events.request.system.SystemPublicDispatchTypeRequest;
import com.example.service.events.response.system.SystemPublicDispatchTypeResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Dispatch Types")
@RequestMapping("/system/public_dispatch_type")
@RequiredArgsConstructor
public class SystemPublicDispatchTypeController {

    private final SystemPublicDispatchTypeService systemPublicDispatchTypeService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicDispatchTypeResponse.Create create(
            @Parameter(hidden = true) SystemPublicDispatchTypeRequest.Context context,
            @RequestBody SystemPublicDispatchTypeRequest.Create request
    ) {
        return systemPublicDispatchTypeService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicDispatchTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicDispatchTypeRequest.Context context,
            @RequestBody SystemPublicDispatchTypeRequest.Id key
    ) {
        return systemPublicDispatchTypeService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicDispatchTypeResponse.Update update(
            @Parameter(hidden = true) SystemPublicDispatchTypeRequest.Context context,
            @RequestBody SystemPublicDispatchTypeRequest.Update request
    ) {
        return systemPublicDispatchTypeService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicDispatchTypeResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicDispatchTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicDispatchTypeRequest.Id recordKey = SystemPublicDispatchTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchTypeService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicDispatchTypeResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicDispatchTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id,
            @RequestBody SystemPublicDispatchTypeRequest.Replace request
    ) {
        SystemPublicDispatchTypeRequest.Id recordKey = SystemPublicDispatchTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchTypeService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicDispatchTypeModel> select(
            @Parameter(hidden = true) SystemPublicDispatchTypeRequest.Context context,
            @RequestBody SystemPublicDispatchTypeRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicDispatchTypeService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAuthority('realm:admin')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicDispatchTypeModel get(
            @Parameter(hidden = true) SystemPublicDispatchTypeRequest.Context context,
            @PathVariable("id") java.lang.Integer id
    ) {
        SystemPublicDispatchTypeRequest.Id recordKey = SystemPublicDispatchTypeRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicDispatchTypeService.get(context, recordKey);
    }
}
