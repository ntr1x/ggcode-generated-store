package com.example.service.security.converter;

import lombok.RequiredArgsConstructor;
import com.example.service.security.model.PrincipalModel;
import org.ntr1x.common.api.component.AppConverter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.stream.Collectors;

public interface PrincipalConverter {

    @RequiredArgsConstructor
    @Component("serviceSecurity.principalConverter.toModel")
    class ToModel implements AppConverter<Principal, PrincipalModel> {
        public PrincipalModel convert(Principal source) {
            try {
                if (source instanceof JwtAuthenticationToken token) {
                    Jwt jwt = token.getToken();

                    return PrincipalModel
                            .builder()
                            .issuer(jwt.getIssuer().toURI())
                            .subject(jwt.getSubject())
                            .username(jwt.getClaimAsString("preferred_username"))
                            .fullName(jwt.getClaimAsString("name"))
                            .email(jwt.getClaimAsString("email"))
                            .token(jwt.getTokenValue())
                            .authorities(token
                                    .getAuthorities()
                                    .stream()
                                    .map(a -> a.getAuthority())
                                    .collect(Collectors.toList()))
                            .build();
                }
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e);
            }

            throw new IllegalArgumentException();
        }
    }
}
