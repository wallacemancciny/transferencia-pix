package com.sistemabancario.transferenciapix.dto;

public record UserDetailResponseDTO(
        String email,
        String telefone,
        String endereco,
        String cep,
        int numeroResidencia,
        String tipo
) {
}
