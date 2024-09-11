package com.example.gateway.events.service.notification.push;

import com.example.gateway.events.service.notification.IPushService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class FirebasePushService implements IPushService {

    @Autowired(required = false)
    private FirebaseMessaging firebaseMessaging;

    @Override
    public Mono<Void> sendPushNotification(String deviceToken, String title, String body) {
        return Mono.fromRunnable(() -> {
            if (firebaseMessaging != null) {
                try {
                    log.info("Attempting to send FCM message. Token: {}", deviceToken);
                    Message.Builder messageBuilder = Message.builder().setToken(deviceToken);

                    if (title != null || body != null) {
                        Notification.Builder notificationBuilder = Notification.builder();
                        if (title != null) {
                            notificationBuilder.setTitle(title);
                        }
                        if (body != null) {
                            notificationBuilder.setBody(body);
                        }
                        messageBuilder.setNotification(notificationBuilder.build());
                    }

                    String response = firebaseMessaging.send(messageBuilder.build());
                    log.info("Successfully sent FCM message: {}", response);
                } catch (Exception e) {
                    log.error("Failed to send FCM message. Token: {}", deviceToken, e);
                    throw new RuntimeException("Failed to send FCM message", e);
                }
            } else {
                log.warn("No Firebase transport initialized. Skipping event.");
            }
        });
    }
}