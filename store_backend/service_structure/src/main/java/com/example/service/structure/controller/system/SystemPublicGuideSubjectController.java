package com.example.service.structure.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.model.system.SystemPublicGuideSubjectModel;
import com.example.service.structure.service.system.SystemPublicGuideSubjectService;
import com.example.service.structure.request.system.SystemPublicGuideSubjectRequest;
import com.example.service.structure.response.system.SystemPublicGuideSubjectResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Guide Subjects")
@RequestMapping("/system/public_guide_subject")
@RequiredArgsConstructor
public class SystemPublicGuideSubjectController {

    private final SystemPublicGuideSubjectService systemPublicGuideSubjectService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicGuideSubjectResponse.Create create(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @RequestBody @Valid SystemPublicGuideSubjectRequest.Create request
    ) {
        return systemPublicGuideSubjectService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicGuideSubjectResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @RequestBody @Valid SystemPublicGuideSubjectRequest.Id key
    ) {
        return systemPublicGuideSubjectService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicGuideSubjectResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicGuideSubjectRequest.Id> keys
    ) {
        return systemPublicGuideSubjectService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicGuideSubjectResponse.Update update(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @RequestBody SystemPublicGuideSubjectRequest.Update request
    ) {
        return systemPublicGuideSubjectService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicGuideSubjectResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicGuideSubjectRequest.Id recordKey = SystemPublicGuideSubjectRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicGuideSubjectService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicGuideSubjectResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicGuideSubjectRequest.Replace request
    ) {
        SystemPublicGuideSubjectRequest.Id recordKey = SystemPublicGuideSubjectRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicGuideSubjectService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicGuideSubjectModel> select(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @RequestBody @Valid SystemPublicGuideSubjectRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicGuideSubjectService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicGuideSubjectModel get(
            @Parameter(hidden = true) SystemPublicGuideSubjectRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicGuideSubjectRequest.Id recordKey = SystemPublicGuideSubjectRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicGuideSubjectService.get(context, recordKey);
    }
}
