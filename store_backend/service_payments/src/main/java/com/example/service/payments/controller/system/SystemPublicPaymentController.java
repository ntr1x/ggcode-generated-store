package com.example.service.payments.controller.system;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.model.system.SystemPublicPaymentModel;
import com.example.service.payments.service.system.SystemPublicPaymentService;
import com.example.service.payments.request.system.SystemPublicPaymentRequest;
import com.example.service.payments.response.system.SystemPublicPaymentResponse;

import org.ntr1x.common.api.views.Views;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

@RestController
@Tag(name = "Payments")
@RequestMapping("/system/public_payment")
@RequiredArgsConstructor
public class SystemPublicPaymentController {

    private final SystemPublicPaymentService systemPublicPaymentService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Create.class)
    public SystemPublicPaymentResponse.Create create(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @RequestBody @Valid SystemPublicPaymentRequest.Create request
    ) {
        return systemPublicPaymentService.create(context, request);
    }
    
    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPaymentResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @RequestBody @Valid SystemPublicPaymentRequest.Id key
    ) {
        return systemPublicPaymentService.remove(context, key);
    }
    
    @PostMapping("/removeAll")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPaymentResponse.RemoveAll removeAll(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @RequestBody @Valid Collection<SystemPublicPaymentRequest.Id> keys
    ) {
        return systemPublicPaymentService.removeAll(context, keys);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Update.class)
    public SystemPublicPaymentResponse.Update update(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @RequestBody SystemPublicPaymentRequest.Update request
    ) {
        return systemPublicPaymentService.update(context, request);
    }
    
    @DeleteMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Remove.class)
    public SystemPublicPaymentResponse.Remove remove(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicPaymentRequest.Id recordKey = SystemPublicPaymentRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPaymentService.remove(context, recordKey);
    }
    
    @PutMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Replace.class)
    public SystemPublicPaymentResponse.Replace replace(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @PathVariable("id") java.util.UUID id,
            @RequestBody @Valid SystemPublicPaymentRequest.Replace request
    ) {
        SystemPublicPaymentRequest.Id recordKey = SystemPublicPaymentRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPaymentService.replace(context, recordKey, request);
    }
    
    @PostMapping("/select")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public Page<SystemPublicPaymentModel> select(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @RequestBody @Valid SystemPublicPaymentRequest.Select request,
            @ParameterObject Pageable pageable
    ) {
        return systemPublicPaymentService.select(context, request, pageable);
    }
    
    @GetMapping("/i/{id}")
    @PreAuthorize("hasAnyAuthority('realm:developer', 'realm:admin', 'realm:support')")
    @SecurityRequirement(name = "Bearer")
    @JsonView(Views.Select.class)
    public SystemPublicPaymentModel get(
            @Parameter(hidden = true) SystemPublicPaymentRequest.Context context,
            @PathVariable("id") java.util.UUID id
    ) {
        SystemPublicPaymentRequest.Id recordKey = SystemPublicPaymentRequest.Id.builder()
                .id(id)
                .build();
        return systemPublicPaymentService.get(context, recordKey);
    }
}
