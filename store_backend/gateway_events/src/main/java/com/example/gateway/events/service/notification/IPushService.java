package com.example.gateway.events.service.notification;

import reactor.core.publisher.Mono;

public interface IPushService {
    Mono<Void> sendPushNotification(String deviceToken, String title, String body);
}
