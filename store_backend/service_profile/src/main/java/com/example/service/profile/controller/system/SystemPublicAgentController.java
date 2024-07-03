package com.example.service.profile.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.model.system.SystemPublicAgentModel;
import com.example.service.profile.service.system.SystemPublicAgentService;
import com.example.service.profile.request.system.SystemPublicAgentRequest;
import com.example.service.profile.response.system.SystemPublicAgentResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Agents")
@RequestMapping("/system/public_agent")
@RequiredArgsConstructor
public class SystemPublicAgentController {

    private final SystemPublicAgentService systemPublicAgentService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicAgentResponse.Create create(
            @Parameter(hidden = true) SystemPublicAgentRequest.Context context,
            @RequestBody SystemPublicAgentRequest.Create request
    ) {
        return systemPublicAgentService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicAgentResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicAgentRequest.Context context,
            @RequestBody SystemPublicAgentRequest.Id key
    ) {
        return systemPublicAgentService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicAgentResponse.Update update(
            @Parameter(hidden = true) SystemPublicAgentRequest.Context context,
            @RequestBody SystemPublicAgentRequest.Update request
    ) {
        return systemPublicAgentService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicAgentResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicAgentRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicAgentRequest.Id recordKey = SystemPublicAgentRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicAgentService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicAgentResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicAgentRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody SystemPublicAgentRequest.Replace request
    ) {
        SystemPublicAgentRequest.Id recordKey = SystemPublicAgentRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicAgentService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicAgentModel> select(
            @Parameter(hidden = true) SystemPublicAgentRequest.Context context,
            @RequestBody SystemPublicAgentRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicAgentService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicAgentModel get(
            @Parameter(hidden = true) SystemPublicAgentRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicAgentRequest.Id recordKey = SystemPublicAgentRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicAgentService.get(context, recordKey);
    }
}
