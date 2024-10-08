package com.example.service.security.service.open;

import com.example.service.security.model.PrincipalModel;
import com.example.service.security.request.open.OpenSecurityRequest;
import com.example.service.security.response.open.OpenSecurityResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.ntr1x.common.security.config.OpenidConfig;
import org.ntr1x.common.security.property.OpenidClientProperty;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenSecurityService {

    private final OpenidConfig openidConfig;
    private final ConversionService conversionService;
    private final JwtDecoder jwtDecoder;

    public OpenSecurityResponse.Auth auth(OpenSecurityRequest.Auth authRequest) {
        OpenidClientProperty config = openidConfig.getClient();

        String redirectUrl = Optional
                .ofNullable(authRequest.getRedirectUri())
                .orElse(config.getRedirectUri());

        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl(config.getIssuerUri())
                .path(config.getAuthorizationPath())
                .queryParam("response_type", "code")
                .queryParam("client_id", config.getClientId())
                .queryParamIfPresent("kc_idp_hint", Optional.ofNullable(authRequest.getProvider()))
                .queryParamIfPresent("state", Optional.ofNullable(authRequest.getState()))
                .queryParam("redirect_uri", redirectUrl)
                .build();

        return OpenSecurityResponse.Auth
                .builder()
                .authUri(uri.toUriString())
                .build();
    }

    public OpenSecurityResponse.Refresh refresh(OpenSecurityRequest.Refresh refreshRequest) {
        OpenidClientProperty config = openidConfig.getClient();

        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

        bodyValues.add("grant_type", "refresh_token");
        bodyValues.add("client_id", config.getClientId());
        bodyValues.add("client_secret", config.getClientSecret());
        bodyValues.add("refresh_token", refreshRequest.getRefreshToken());

        return WebClient
                .builder()
                .baseUrl(config.getIssuerUri())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build()
                .post()
                .uri(config.getTokenPath())
                .body(BodyInserters.fromFormData(bodyValues))
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, clientResponse -> Mono.error(
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, clientResponse.toString())
                ))
                .bodyToMono(KeycloakTokenResponse.class)
                .map(response -> {
                    Jwt jwt = jwtDecoder.decode(response.getAccessToken());
                    PrincipalModel principal = conversionService.convert(jwt, PrincipalModel.class);
                    return OpenSecurityResponse.Refresh
                            .builder()
                            .accessToken(response.getAccessToken())
                            .accessExpiresIn(response.getExpiresIn())
                            .refreshToken(response.getRefreshToken())
                            .refreshExpiresIn(response.getRefreshExpiresIn())
                            .principal(principal)
                            .build();
                })
                .block(Duration.ofSeconds(5));
    }

    public OpenSecurityResponse.Revoke revoke(OpenSecurityRequest.Revoke revokeRequest) {
        OpenidClientProperty config = openidConfig.getClient();

        Mono<String> revokeAccessToken = Optional
                .ofNullable(revokeRequest.getAccessToken())
                .map(token -> {
                    MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

                    bodyValues.add("client_id", openidConfig.getClient().getClientId());
                    bodyValues.add("client_secret", config.getClientSecret());
                    bodyValues.add("token", revokeRequest.getAccessToken());
                    bodyValues.add("token_type_hint", "access_token");

                    return WebClient
                            .builder()
                            .baseUrl(config.getIssuerUri())
                            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                            .build()
                            .post()
                            .uri(config.getRevokePath())
                            .body(BodyInserters.fromFormData(bodyValues))
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .orElse(Mono.empty());

        Mono<String> revokeRefreshToken = Optional
                .ofNullable(revokeRequest.getRefreshToken())
                .map(token -> {
                    MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

                    bodyValues.add("client_id", config.getClientId());
                    bodyValues.add("client_secret", config.getClientSecret());
                    bodyValues.add("token", revokeRequest.getRefreshToken());
                    bodyValues.add("token_type_hint", "refresh_token");

                    return WebClient
                            .builder()
                            .baseUrl(config.getIssuerUri())
                            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                            .build()
                            .post()
                            .uri(config.getRevokePath())
                            .body(BodyInserters.fromFormData(bodyValues))
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .orElse(Mono.empty());

        return Mono
                .zip(revokeAccessToken, revokeRefreshToken)
                .map(r -> OpenSecurityResponse.Revoke
                        .builder()
                        .accessTokenRevoked(r.getT1() != null)
                        .refreshTokenRevoked(r.getT2() != null)
                        .build())
                .block(Duration.ofSeconds(5));
    }

    public OpenSecurityResponse.Callback callback(OpenSecurityRequest.Callback callbackRequest) {
        OpenidClientProperty config = openidConfig.getClient();

        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

        bodyValues.add("grant_type", "authorization_code");
        bodyValues.add("client_id", config.getClientId());
        bodyValues.add("client_secret", config.getClientSecret());
        bodyValues.add("code", callbackRequest.getCode());
        bodyValues.add("redirect_uri", Optional
                .ofNullable(callbackRequest.getRedirectUri())
                .orElse(config.getRedirectUri())
        );

        return WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().wiretap(true)
                ))
                .baseUrl(config.getIssuerUri())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build()
                .post()
                .uri(config.getTokenPath())
                .body(BodyInserters.fromFormData(bodyValues))
                .retrieve()
                .bodyToMono(KeycloakTokenResponse.class)
                .map(response -> {
                    Jwt jwt = jwtDecoder.decode(response.getAccessToken());
                    PrincipalModel principal = conversionService.convert(jwt, PrincipalModel.class);
                    return OpenSecurityResponse.Callback
                            .builder()
                            .accessToken(response.getAccessToken())
                            .accessExpiresIn(response.getExpiresIn())
                            .refreshToken(response.getRefreshToken())
                            .refreshExpiresIn(response.getRefreshExpiresIn())
                            .principal(principal)
                            .build();
                })
                .doOnError(err -> log.warn("Failed to authenticate", err))
                .block();
    }

    public OpenSecurityResponse.SignIn signIn(OpenSecurityRequest.SignIn signInRequest) {
        OpenidClientProperty config = openidConfig.getClient();

        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

        bodyValues.add("grant_type", "password");
        bodyValues.add("client_id", config.getClientId());
        bodyValues.add("client_secret", config.getClientSecret());
        bodyValues.add("username", signInRequest.getUsername());
        bodyValues.add("password", signInRequest.getPassword());

        try {
            return WebClient
                    .builder()
                    .clientConnector(new ReactorClientHttpConnector(
                            HttpClient.create().wiretap(true)
                    ))
                    .baseUrl(config.getIssuerUri())
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .build()
                    .post()
                    .uri(config.getTokenPath())
                    .body(BodyInserters.fromFormData(bodyValues))
                    .retrieve()
                    .bodyToMono(KeycloakTokenResponse.class)
                    .map(response -> {
                        Jwt jwt = jwtDecoder.decode(response.getAccessToken());
                        PrincipalModel principal = conversionService.convert(jwt, PrincipalModel.class);
                        return OpenSecurityResponse.SignIn
                                .builder()
                                .accessToken(response.getAccessToken())
                                .accessExpiresIn(response.getExpiresIn())
                                .refreshToken(response.getRefreshToken())
                                .refreshExpiresIn(response.getRefreshExpiresIn())
                                .principal(principal)
                                .build();
                    })
                    .doOnError(err -> log.warn("Failed to authenticate", err))
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class KeycloakTokenResponse {
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("expires_in")
        private Long expiresIn;
        @JsonProperty("refresh_token")
        private String refreshToken;
        @JsonProperty("refresh_expires_in")
        private Long refreshExpiresIn;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("id_token")
        private String idToken;
        @JsonProperty("not-before-policy")
        private Long notBeforePolicy;
        @JsonProperty("session_state")
        private String sessionState;
        private String scope;
    }
}
