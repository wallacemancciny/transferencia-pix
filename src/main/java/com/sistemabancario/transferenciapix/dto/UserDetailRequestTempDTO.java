package com.sistemabancario.transferenciapix.dto;

public record UserDetailRequestTempDTO(
        String userId,
        String email,
        String telefone,
        String endereco,
        String cep,
        int numeroResidencia,
        String tipo
) {
}
