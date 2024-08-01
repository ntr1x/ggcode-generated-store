package com.example.gateway.events.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final FirebaseService firebaseService;
    private final HuaweiService huaweiService;
    public void sendNotification(String deviceToken, String title, String body, Integer typeId){
        switch (typeId) {
            case 1:
                firebaseService.sendFirebasePushNotification(deviceToken, title, body);
                break;
            case 2:
                huaweiService.sendHuaweiPushNotification(deviceToken, title, body).block(Duration.ofSeconds(5));
                break;
            default:
                log.warn("Invalid notification type: {}", typeId);
                break;
        }
    }
}
