package com.example.gateway.events.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class HuaweiService {
    private final WebClient.Builder webClientBuilder;

    @Value("${huawei.push.client-id:111569649}")
    private String clientId;

    @Value("${huawei.push.client-secret:fd7c6891686f73fd66a7661b2bc971c04fa7f058b431eb0e35526da1549c2c76}")
    private String clientSecret;

    @Value("${huawei.push.token-url:https://oauth-login.cloud.huawei.com/oauth2/v3/token}")
    private String tokenUrl;

    @Value("#{'${huawei.push.api-base-url:https://push-api.cloud.huawei.com/v1}' + '/' + '${huawei.push.client-id:111569649}' + '/messages:send'}")
    private String apiUrl;
    private final AtomicReference<TokenInfo> tokenInfo = new AtomicReference<>(null);

    // Все исключения в методах должны передаваться выше по цепочке в PublicDispatchService
    public Mono<Void> sendHuaweiPushNotification(String deviceToken, String title, String body) {
        log.info("Attempting to send Huawei push notification. Device token: {}", deviceToken);

        if (deviceToken == null || deviceToken.isEmpty()) {
            log.error("Device token is null or empty");
            return Mono.error(new IllegalArgumentException("Device token cannot be null or empty"));
        }

        String safeTitle = (title != null && !title.isEmpty()) ? title : "Уведомление";
        String safeBody = (body != null && !body.isEmpty()) ? body : "У вас новое уведомление";

        if (!safeTitle.equals(title)) {
            log.warn("Null or empty title provided for push notification. Using default: {}", safeTitle);
        }
        if (!safeBody.equals(body)) {
            log.warn("Null or empty body provided for push notification. Using default: {}", safeBody);
        }

        return getValidAccessToken()
                .flatMap(token -> sendNotification(token, deviceToken, safeTitle, safeBody))
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                        .filter(this::isTokenExpired)
                        .doBeforeRetry(s -> {
                            log.warn("Token expired. Clearing cached token and retrying...");
                            tokenInfo.set(null);
                        }))
                .doOnSuccess(v -> log.info("Successfully sent Huawei push notification to device: {}", deviceToken))
                .doOnError(e -> log.error("Failed to send Huawei push notification to device: {}. Error: {}", deviceToken, e.getMessage()));
    }

    // Мы ограничены API Huawei. Только 1000 токенов в 5 минут, поэтому есть необходимость сохранять токен и переиспользовать
    private Mono<String> getValidAccessToken() {
        TokenInfo currentTokenInfo = tokenInfo.get();
        if (currentTokenInfo != null && currentTokenInfo.isValid()) {
            log.debug("Using cached access token");
            return Mono.just(currentTokenInfo.token);
        }
        log.info("Fetching new access token");
        return getAccessToken()
                .doOnNext(token -> {
                    Instant expiryTime = Instant.now().plusSeconds(3500); // Намеренно проставляю время чуть меньше, чем 3600
                    tokenInfo.set(new TokenInfo(token, expiryTime));
                    log.info("New access token obtained and cached. Expiry time: {}", expiryTime);
                });
    }

    private Mono<String> getAccessToken() {
        log.debug("Requesting new access token from Huawei API");
        return webClientBuilder.build()
                .post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                        clientId, clientSecret))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("access_token"))
                .doOnSuccess(token -> log.debug("Successfully obtained new access token"))
                .doOnError(e -> log.error("Failed to obtain access token. Error: {}", e.getMessage()));
    }

    private Mono<Void> sendNotification(String accessToken, String deviceToken, String title, String body) {
        log.debug("Sending notification to device: {}. Title: {}. Body: {}", deviceToken, title, body);
        Map<String, Object> requestBody = createNotificationPayload(deviceToken, title, body);

        return webClientBuilder.build()
                .post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(v -> log.debug("Notification sent successfully to device: {}", deviceToken))
                .doOnError(e -> log.error("Failed to send notification to device: {}. Error: {}", deviceToken, e.getMessage()));
    }

    private Map<String, Object> createNotificationPayload(String deviceToken, String title, String body) {
        Map<String, Object> message = new HashMap<>();
        message.put("token", Collections.singletonList(deviceToken));

        Map<String, Object> notification = new HashMap<>();
        notification.put("title", title);
        notification.put("body", body);

        Map<String, Object> clickAction = new HashMap<>();
        clickAction.put("type", 3);
        notification.put("click_action", clickAction);

        Map<String, Object> android = new HashMap<>();
        android.put("notification", notification);

        message.put("android", android);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("validate_only", false);
        requestBody.put("message", message);

        return requestBody;
    }

    // Huawei в случае ошибки возвращает HTTP 200, передавая код ошибки в теле ответа https://developer.huawei.com/consumer/en/doc/HMSCore-References/https-send-api-0000001050986197#section13968115715131
    private boolean isTokenExpired(Throwable throwable) {
        boolean expired = throwable instanceof WebClientResponseException &&
                ((WebClientResponseException) throwable).getStatusCode() == HttpStatus.OK &&
                ((WebClientResponseException) throwable).getResponseBodyAsString().contains("80200003");

        if (expired) {
            log.warn("Access token has expired");
        }
        return expired;
    }

    private static class TokenInfo {
        final String token;
        final Instant expiryTime;

        TokenInfo(String token, Instant expiryTime) {
            this.token = token;
            this.expiryTime = expiryTime;
        }

        boolean isValid() {
            return Instant.now().isBefore(expiryTime);
        }
    }
}

