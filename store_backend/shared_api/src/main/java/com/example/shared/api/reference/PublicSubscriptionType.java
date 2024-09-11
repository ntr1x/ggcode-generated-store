package com.example.shared.api.reference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum PublicSubscriptionType {
    FCM(1),
    HMS(2),
    SMS(3),
    WS(4),
    EMAIL(5);

    private final int code;

    public static Optional<PublicSubscriptionType> fromCode(Integer code) {
        if (code == null) {
            return Optional.empty();
        }
        return Arrays
                .stream(values())
                .filter(t -> t.code == code)
                .findFirst();
    }
}
