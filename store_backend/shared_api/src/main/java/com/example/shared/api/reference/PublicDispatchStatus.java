package com.example.shared.api.reference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum PublicDispatchStatus {
    NEW(1),
    DISPATCHED(2),
    CONFIRMED(3),
    FAILED(4);

    private final int code;

    public static Optional<PublicDispatchStatus> fromCode(Integer code) {
        if (code == null) {
            return Optional.empty();
        }
        return Arrays
                .stream(values())
                .filter(t -> t.code == code)
                .findFirst();
    }
}
