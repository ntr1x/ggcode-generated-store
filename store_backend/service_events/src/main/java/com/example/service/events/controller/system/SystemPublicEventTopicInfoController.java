package com.example.service.events.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.events.model.system.SystemPublicEventTopicInfoModel;
import com.example.service.events.service.system.SystemPublicEventTopicInfoService;
import com.example.service.events.request.system.SystemPublicEventTopicInfoRequest;
import com.example.service.events.response.system.SystemPublicEventTopicInfoResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Event Topics")
@RequestMapping("/system/public_event_topic_info")
@RequiredArgsConstructor
public class SystemPublicEventTopicInfoController {

    private final SystemPublicEventTopicInfoService systemPublicEventTopicInfoService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicEventTopicInfoResponse.Create create(
            @Parameter(hidden = true) SystemPublicEventTopicInfoRequest.Context context,
            @RequestBody @Valid SystemPublicEventTopicInfoRequest.Create request
    ) {
        return systemPublicEventTopicInfoService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicEventTopicInfoResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicEventTopicInfoRequest.Context context,
            @RequestBody @Valid SystemPublicEventTopicInfoRequest.Id key
    ) {
        return systemPublicEventTopicInfoService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicEventTopicInfoResponse.Update update(
            @Parameter(hidden = true) SystemPublicEventTopicInfoRequest.Context context,
            @RequestBody SystemPublicEventTopicInfoRequest.Update request
    ) {
        return systemPublicEventTopicInfoService.update(context, request);
    }
    
    @DeleteMapping("/i/{name}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicEventTopicInfoResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicEventTopicInfoRequest.Context context,
            @PathVariable("name") java.lang.String name
    ) {
        SystemPublicEventTopicInfoRequest.Id recordKey = SystemPublicEventTopicInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventTopicInfoService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{name}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicEventTopicInfoResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicEventTopicInfoRequest.Context context,
            @PathVariable("name") java.lang.String name,
            @RequestBody @Valid SystemPublicEventTopicInfoRequest.Replace request
    ) {
        SystemPublicEventTopicInfoRequest.Id recordKey = SystemPublicEventTopicInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventTopicInfoService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicEventTopicInfoModel> select(
            @Parameter(hidden = true) SystemPublicEventTopicInfoRequest.Context context,
            @RequestBody @Valid SystemPublicEventTopicInfoRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicEventTopicInfoService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{name}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicEventTopicInfoModel get(
            @Parameter(hidden = true) SystemPublicEventTopicInfoRequest.Context context,
            @PathVariable("name") java.lang.String name
    ) {
        SystemPublicEventTopicInfoRequest.Id recordKey = SystemPublicEventTopicInfoRequest.Id.builder()
                .name(name)
                .build();
        return systemPublicEventTopicInfoService.get(context, recordKey);
    }
}
