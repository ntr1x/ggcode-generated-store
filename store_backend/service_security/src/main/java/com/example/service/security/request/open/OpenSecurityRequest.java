package com.example.service.security.request.open;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface OpenSecurityRequest {

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "OpenSecurityAuthRequest")
    class Auth {
        @Hidden
        @Builder.Default
        private String __name = "open_security:request:auth";

        private String state;
        private String provider;
        private String redirectUri;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "OpenSecurityResponseSignIn")
    class SignIn {
        @Hidden
        @Builder.Default
        private String __name = "open_security:request:sign_in";

        private String username;
        private String password;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "OpenSecurityRefreshRequest")
    class Refresh {
        @Hidden
        @Builder.Default
        private String __name = "open_security:request:refresh";

        private String refreshToken;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "OpenSecurityRevokeRequest")
    class Revoke {
        @Hidden
        @Builder.Default
        private String __name = "open_security:request:revoke";

        private String accessToken;
        private String refreshToken;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "OpenSecurityCallbackRequest")
    class Callback {
        @Hidden
        @Builder.Default
        private String __name = "open_security:request:callback";

        private String state;
        private String code;
        private String redirectUri;
    }
}
