package com.example.gateway.events.service.notification.push;

import com.example.gateway.events.service.notification.IPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class HuaweiPushService implements IPushService {

    @Autowired(required = false)
    private HuaweiMessaging huaweiMessaging;

    @Override
    public Mono<Void> sendPushNotification(String deviceToken, String title, String body) {
        if (huaweiMessaging != null) {
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

            HuaweiMessaging.Message message = HuaweiMessaging.Message
                    .builder()
                    .deviceToken(deviceToken)
                    .safeTitle(safeTitle)
                    .safeBody(safeBody)
                    .build();

            return huaweiMessaging.send(message);
        } else {
            log.warn("No Huawei transport initialized. Skipping event.");
        }
        return Mono.empty();
    }
}

