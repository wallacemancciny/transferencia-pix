package com.sistemabancario.transferenciapix.dto;

public record UserDetailRequestDTO(
        String cep,
        int numeroResidencia,
        String tipo
) {
}
