package com.sistemabancario.transferenciapix.dto;

public record DashboardDTO(
        Long totalPix,
        Long totalEmprestimos,
        Long totalContas,
        Long totalClientes,
        Long TotalCartoes,
        Long totalNotificacoes) {
}
