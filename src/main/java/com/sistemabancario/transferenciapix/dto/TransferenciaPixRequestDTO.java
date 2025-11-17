package com.sistemabancario.transferenciapix.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

// DTO usado para criar uma nova transferência Pix
public class TransferenciaPixRequestDTO {

    @NotBlank(message = "Chave origem não pode ficar vazio")
    private String chaveOrigem;
    private String nomeOrigem;
    private String bancoOrigem;

    private String chaveDestino;
    private String nomeDestino;

    private BigDecimal valor;

    // 🔹 Construtor padrão
    public TransferenciaPixRequestDTO() {}

    // 🔹 Construtor completo (opcional)
    public TransferenciaPixRequestDTO(String chaveOrigem, String nomeOrigem, String bancoOrigem,
                                      String chaveDestino, String nomeDestino, BigDecimal valor) {
        this.chaveOrigem = chaveOrigem;
        this.nomeOrigem = nomeOrigem;
        this.bancoOrigem = bancoOrigem;
        this.chaveDestino = chaveDestino;
        this.nomeDestino = nomeDestino;
        this.valor = valor;
    }

    // 🔹 Getters e Setters
    public String getChaveOrigem() { return chaveOrigem; }
    public void setChaveOrigem(String chaveOrigem) { this.chaveOrigem = chaveOrigem; }

    public String getNomeOrigem() { return nomeOrigem; }
    public void setNomeOrigem(String nomeOrigem) { this.nomeOrigem = nomeOrigem; }

    public String getBancoOrigem() { return bancoOrigem; }
    public void setBancoOrigem(String bancoOrigem) { this.bancoOrigem = bancoOrigem; }

    public String getChaveDestino() { return chaveDestino; }
    public void setChaveDestino(String chaveDestino) { this.chaveDestino = chaveDestino; }

    public String getNomeDestino() { return nomeDestino; }
    public void setNomeDestino(String nomeDestino) { this.nomeDestino = nomeDestino; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}
