package com.sistemabancario.transferenciapix.dto;

import com.sistemabancario.transferenciapix.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
