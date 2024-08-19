package com.example.gateway.events.service.notification;

import com.example.gateway.events.service.notification.push.FirebasePushService;
import com.example.gateway.events.service.notification.push.HuaweiPushService;
import com.example.shared.api.reference.PublicSubscriptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final FirebasePushService firebaseService;
    private final HuaweiPushService huaweiService;
    private final SmsService smsService;

    public Mono<Void> sendNotification(String deviceToken, String title, String body, PublicSubscriptionType type) {
        return getNotificationMono(deviceToken, title, body, type)
                .doOnSuccess(v -> log.info("Notification sent successfully. Type: {}", type))
                .doOnError(e -> log.error("Failed to send notification. Type: {}", type, e));
    }

    private Mono<Void> getNotificationMono(String deviceToken, String title, String body, PublicSubscriptionType type) {
        return switch (type) {
            case FCM -> firebaseService.sendPushNotification(deviceToken, title, body);
            case HMS -> huaweiService.sendPushNotification(deviceToken, title, body);
            case SMS -> smsService.sendSms(deviceToken, body);
            case WS, EMAIL -> throw new UnsupportedOperationException("Notification type not implemented: " + type);
        };
    }
}
