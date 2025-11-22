package com.sistemabancario.transferenciapix.dto;

import java.time.Instant;

public record OutputSuccessDTO<T>(
        boolean success,
        Instant timestamp,
        T data
) {
    public OutputSuccessDTO(T data) {
        this(true, Instant.now(), data);
    }
    public OutputSuccessDTO(boolean success, T data) {
        this(success, Instant.now(), data);
    }
}

