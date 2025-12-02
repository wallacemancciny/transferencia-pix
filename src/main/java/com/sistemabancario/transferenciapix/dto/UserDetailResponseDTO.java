package com.sistemabancario.transferenciapix.dto;

public record UserDetailResponseDTO(
        String cep,
        String email,
        String telefone,
        String endereco,
        String cidade,
        String bairro,
        int numeroResidencia,
        String tipo
) {
}
