package com.sistemabancario.transferenciapix.dto;

public record UserDetailRequestDTO(
        String cep,
        String userId,
        String email,
        String telefone,
        int numeroResidencia,
        String tipo
) {
}
