package com.example.service.catalog.controller.anonymous;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.model.anonymous.AnonymousPublicItemModel;
import com.example.service.catalog.service.anonymous.AnonymousPublicItemService;
import com.example.service.catalog.request.anonymous.AnonymousPublicItemRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicItemResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Public")
@RequestMapping("/anonymous/public_item")
@RequiredArgsConstructor
public class AnonymousPublicItemController {

    private final AnonymousPublicItemService anonymousPublicItemService;
    
    @PostMapping("/select")
    @JsonView(Views.Select.class)
    public Page<AnonymousPublicItemModel> select(
            @Parameter(hidden = true) AnonymousPublicItemRequest.Context context,
            @RequestBody @Valid AnonymousPublicItemRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return anonymousPublicItemService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @JsonView(Views.Select.class)
    public AnonymousPublicItemModel get(
            @Parameter(hidden = true) AnonymousPublicItemRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        AnonymousPublicItemRequest.Id recordKey = AnonymousPublicItemRequest.Id.builder()
                .id(id)
                .build();
        return anonymousPublicItemService.get(context, recordKey);
    }
}
