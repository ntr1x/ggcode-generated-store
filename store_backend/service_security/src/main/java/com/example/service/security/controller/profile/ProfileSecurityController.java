package com.example.service.security.controller.profile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.example.service.security.model.PrincipalModel;
import com.example.service.security.service.profile.ProfileSecurityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Tag(name = "Security")
@RequestMapping("/security")
@RequiredArgsConstructor
public class ProfileSecurityController {

    private final ProfileSecurityService profileSecurityService;

    @GetMapping("/me/jwt")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Get JWT data")
    public Jwt jwt(Principal principal) {
        return ((JwtAuthenticationToken) principal).getToken();
    }

    @GetMapping("/me/principal")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Get principal data")
    public PrincipalModel principalModel(@Parameter(hidden = true) PrincipalModel principalModel) {
        return principalModel;
    }
}
