package com.example.gateway.events.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${firebase.config-file}")
    private Resource firebaseConfigFile;

    @Bean
    GoogleCredentials googleCredentials() {
        try {
            return GoogleCredentials.fromStream(firebaseConfigFile.getInputStream());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Bean
    FirebaseApp firebaseApp(GoogleCredentials googleCredentials) {
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();

        return FirebaseApp.initializeApp(firebaseOptions);
    }

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp app) {
        return FirebaseMessaging.getInstance(app);
    }
}
