package com.sistemabancario.transferenciapix.dto;

import com.sistemabancario.transferenciapix.entity.User;

public record UserResponseDTO(
        User user,
        String email,
        String telefone,
        String cep,
        String endereco,
        String cidade,
        String bairro,
        int numeroResidencia,
        String tipo
) {
}
