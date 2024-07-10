package com.example.service.payments.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.model.system.SystemPublicOrderModel;
import com.example.service.payments.service.system.SystemPublicOrderService;
import com.example.service.payments.request.system.SystemPublicOrderRequest;
import com.example.service.payments.response.system.SystemPublicOrderResponse;

import org.ntr1x.common.api.views.Views;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "Orders")
@RequestMapping("/system/public_order")
@RequiredArgsConstructor
public class SystemPublicOrderController {

    private final SystemPublicOrderService systemPublicOrderService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicOrderResponse.Create create(
            @Parameter(hidden = true) SystemPublicOrderRequest.Context context,
            @RequestBody @Valid SystemPublicOrderRequest.Create request
    ) {
        return systemPublicOrderService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicOrderResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicOrderRequest.Context context,
            @RequestBody @Valid SystemPublicOrderRequest.Id key
    ) {
        return systemPublicOrderService.remove(context, key);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicOrderResponse.Update update(
            @Parameter(hidden = true) SystemPublicOrderRequest.Context context,
            @RequestBody SystemPublicOrderRequest.Update request
    ) {
        return systemPublicOrderService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicOrderResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicOrderRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicOrderRequest.Id recordKey = SystemPublicOrderRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicOrderService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicOrderResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicOrderRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicOrderRequest.Replace request
    ) {
        SystemPublicOrderRequest.Id recordKey = SystemPublicOrderRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicOrderService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicOrderModel> select(
            @Parameter(hidden = true) SystemPublicOrderRequest.Context context,
            @RequestBody @Valid SystemPublicOrderRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicOrderService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicOrderModel get(
            @Parameter(hidden = true) SystemPublicOrderRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicOrderRequest.Id recordKey = SystemPublicOrderRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicOrderService.get(context, recordKey);
    }
}
