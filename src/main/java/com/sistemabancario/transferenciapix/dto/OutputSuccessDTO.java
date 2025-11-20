package com.sistemabancario.transferenciapix.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputSuccessDTO<T> {

    private boolean success;
    private Instant timestamp;
    private T data;


    public OutputSuccessDTO(T data) {
        this.success = true;
        this.timestamp = Instant.now();
        this.data = data;
    }

}

