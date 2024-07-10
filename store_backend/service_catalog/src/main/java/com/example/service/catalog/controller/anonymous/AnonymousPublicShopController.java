package com.example.service.catalog.controller.anonymous;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.anonymous.AnonymousPublicShopModel;
import com.example.service.catalog.service.anonymous.AnonymousPublicShopService;
import com.example.service.catalog.request.anonymous.AnonymousPublicShopRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicShopResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Public")
@RequestMapping("/anonymous/public_shop")
@RequiredArgsConstructor
public class AnonymousPublicShopController {

    private final AnonymousPublicShopService anonymousPublicShopService;
    
    @PostMapping("/select")
    @JsonView(Views.Select.class)
    public Page<AnonymousPublicShopModel> select(
            @Parameter(hidden = true) AnonymousPublicShopRequest.Context context,
            @RequestBody @Valid AnonymousPublicShopRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return anonymousPublicShopService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @JsonView(Views.Select.class)
    public AnonymousPublicShopModel get(
            @Parameter(hidden = true) AnonymousPublicShopRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        AnonymousPublicShopRequest.Id recordKey = AnonymousPublicShopRequest.Id.builder()
                .id(id)
                .build();
        return anonymousPublicShopService.get(context, recordKey);
    }
}
