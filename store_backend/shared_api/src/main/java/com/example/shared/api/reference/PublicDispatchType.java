package com.example.shared.api.reference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum PublicDispatchType {
    CUSTOMER(1),
    AGENT(2),
    SESSION(3),
    CRITERIA(4);

    private final int code;

    public static Optional<PublicDispatchType> fromCode(Integer code) {
        if (code == null) {
            return Optional.empty();
        }
        return Arrays
                .stream(values())
                .filter(t -> t.code == code)
                .findFirst();
    }
}