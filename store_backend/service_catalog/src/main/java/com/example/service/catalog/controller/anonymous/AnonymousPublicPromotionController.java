package com.example.service.catalog.controller.anonymous;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.anonymous.AnonymousPublicPromotionModel;
import com.example.service.catalog.service.anonymous.AnonymousPublicPromotionService;
import com.example.service.catalog.request.anonymous.AnonymousPublicPromotionRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicPromotionResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Public")
@RequestMapping("/anonymous/public_promotion")
@RequiredArgsConstructor
public class AnonymousPublicPromotionController {

    private final AnonymousPublicPromotionService anonymousPublicPromotionService;
    
    @PostMapping("/select")
    @JsonView(Views.Select.class)
    public Page<AnonymousPublicPromotionModel> select(
            @Parameter(hidden = true) AnonymousPublicPromotionRequest.Context context,
            @RequestBody @Valid AnonymousPublicPromotionRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return anonymousPublicPromotionService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @JsonView(Views.Select.class)
    public AnonymousPublicPromotionModel get(
            @Parameter(hidden = true) AnonymousPublicPromotionRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        AnonymousPublicPromotionRequest.Id recordKey = AnonymousPublicPromotionRequest.Id.builder()
                .id(id)
                .build();
        return anonymousPublicPromotionService.get(context, recordKey);
    }
}
