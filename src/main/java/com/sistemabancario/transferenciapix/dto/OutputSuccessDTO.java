package com.sistemabancario.transferenciapix.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputSuccessDTO {

    private boolean success;
    private TransferenciaPixResponseDTO data;
    private String timestamp;

}

