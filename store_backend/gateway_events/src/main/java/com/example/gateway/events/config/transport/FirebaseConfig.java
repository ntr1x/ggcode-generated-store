package com.example.gateway.events.config.transport;

import com.example.gateway.events.property.FirebaseProperty;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.firebase")
public class FirebaseConfig {

    @Valid
    private FirebaseProperty push;

    @Bean
    @SneakyThrows
    FirebaseMessaging firebaseMessaging() {
        if (push == null) {
            return null;
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(push.getConfigFile().getInputStream());

        FirebaseOptions options = FirebaseOptions
                .builder()
                .setCredentials(credentials)
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);

        return FirebaseMessaging.getInstance(app);
    }
}
