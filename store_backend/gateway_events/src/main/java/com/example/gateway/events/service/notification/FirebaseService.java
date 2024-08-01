package com.example.gateway.events.service.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseService {

    private final FirebaseMessaging firebaseMessaging;

    public void sendFirebasePushNotification(String deviceToken, String title, String body) {
        try {
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
            log.error("Failed to send FCM message", e);
            throw new RuntimeException("Failed to send FCM message", e);
        }
    }
}