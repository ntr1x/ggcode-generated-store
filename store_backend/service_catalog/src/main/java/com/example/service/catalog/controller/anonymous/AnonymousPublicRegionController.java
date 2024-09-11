package com.example.service.catalog.controller.anonymous;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.anonymous.AnonymousPublicRegionModel;
import com.example.service.catalog.service.anonymous.AnonymousPublicRegionService;
import com.example.service.catalog.request.anonymous.AnonymousPublicRegionRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicRegionResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Public")
@RequestMapping("/anonymous/public_region")
@RequiredArgsConstructor
public class AnonymousPublicRegionController {

    private final AnonymousPublicRegionService anonymousPublicRegionService;
    
    @PostMapping("/select")
    @JsonView(Views.Select.class)
    public Page<AnonymousPublicRegionModel> select(
            @Parameter(hidden = true) AnonymousPublicRegionRequest.Context context,
            @RequestBody @Valid AnonymousPublicRegionRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return anonymousPublicRegionService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @JsonView(Views.Select.class)
    public AnonymousPublicRegionModel get(
            @Parameter(hidden = true) AnonymousPublicRegionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        AnonymousPublicRegionRequest.Id recordKey = AnonymousPublicRegionRequest.Id.builder()
                .id(id)
                .build();
        return anonymousPublicRegionService.get(context, recordKey);
    }
}
