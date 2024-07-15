package com.example.service.security.controller.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.example.service.security.request.open.OpenSecurityRequest;
import com.example.service.security.response.open.OpenSecurityResponse;
import com.example.service.security.service.open.OpenSecurityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Security")
@RequestMapping("/public/security")
@RequiredArgsConstructor
public class OpenSecurityController {

    private final OpenSecurityService openSecurityService;

    @PostMapping("/auth")
    @Operation(summary = "Auth")
    public OpenSecurityResponse.Auth auth (
            @RequestBody OpenSecurityRequest.Auth authRequest
    ) {
        return openSecurityService.auth(authRequest);
    }

    @PostMapping("/signIn")
    @Operation(summary = "Sign In")
    public OpenSecurityResponse.SignIn signIn (
            @RequestBody OpenSecurityRequest.SignIn signInRequest
    ) {
        return openSecurityService.signIn(signInRequest);
    }

    @PostMapping("/signUp")
    @Operation(summary = "Sign Up")
    public OpenSecurityResponse.SignUp signUp (
            @RequestBody OpenSecurityRequest.SignUp signUpRequest
    ) {
        return openSecurityService.signUp(signUpRequest);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh user token")
    public OpenSecurityResponse.Refresh refresh (
            @RequestBody OpenSecurityRequest.Refresh refreshRequest
    ) {
        return openSecurityService.refresh(refreshRequest);
    }

    @PostMapping("/revoke")
    @Operation(summary = "Revoke token (logout)")
    public OpenSecurityResponse.Revoke revoke (
            @RequestBody OpenSecurityRequest.Revoke revokeRequest
    ) {
        return openSecurityService.revoke(revokeRequest);
    }

    @PostMapping("/callback")
    @Operation(summary = "Authorize user redirect")
    public OpenSecurityResponse.Callback callback (
            @RequestBody OpenSecurityRequest.Callback callbackRequest
    ) {
        return openSecurityService.callback(callbackRequest);
    }
}
