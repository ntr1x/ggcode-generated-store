package com.example.gateway.events.service.notification;

import com.example.gateway.events.util.PhoneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class SmsService {
    private final WebClient webClient;

    @Value("${tele2.sms.url:https://bsms.tele2.ru/api/}")
    private String tele2Url;

    @Value("${tele2.login:ht986826596}")
    private String login;

    @Value("${tele2.password:WvW4tQ9J}")
    private String password;

    @Value("${tele2.sender:RBT.ru}")
    private String sender;

    public SmsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(tele2Url).build();
    }

    public Mono<Void> sendSms(String phone, String message) {
        log.info("Attempting to send SMS to phone: {}", phone);

        final String normalizedPhone;
        if (PhoneUtils.validatePhone(phone)) {
            normalizedPhone = PhoneUtils.normalizePhone(phone);
        } else {
            log.warn("Invalid phone number: {}", phone);
            return Mono.error(new IllegalArgumentException("Invalid phone number"));
        }

        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String url = tele2Url + buildUrl(normalizedPhone, encodedMessage);

        return webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(this::handleResponse)
                .doOnError(e -> log.error("Error sending SMS to phone: {}. Error: {}", normalizedPhone, e.getMessage()));
    }

    // Tele2 API в случае ошибки вовзращает 200 и body в виде ERROR: message
    private Mono<Void> handleResponse(String response) {
        if (response.startsWith("ERROR:")) {
            log.error("API returned an error: {}", response);
            return Mono.error(new RuntimeException("SMS sending failed: " + response));
        } else if (response.matches("\\d+")) {
            log.info("SMS sent successfully. Response: {}", response);
            return Mono.empty();
        } else {
            log.warn("Unexpected API response: {}", response);
            return Mono.error(new RuntimeException("Unexpected API response: " + response));
        }
    }

    private String buildUrl(String phone, String encodedMessage) {
        return String.format("?operation=send&login=%s&password=%s&msisdn=%s&shortcode=%s&text=%s",
                login, password, phone, sender, encodedMessage);
    }
}
