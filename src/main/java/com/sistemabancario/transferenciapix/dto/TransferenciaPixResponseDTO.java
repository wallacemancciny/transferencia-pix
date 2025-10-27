package com.sistemabancario.transferenciapix.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferenciaPixResponseDTO {

    private String codigoTransacao;
    private String chaveOrigem;
    private String nomeOrigem;
    private String bancoOrigem;
    private String chaveDestino;
    private String nomeDestino;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;
    private String status;
    private String mensagem;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    // 🔹 Construtor padrão (necessário para serialização)
    public TransferenciaPixResponseDTO() {}

    // 🔹 Construtor completo (opcional, útil para mapear manualmente)
    public TransferenciaPixResponseDTO(String codigoTransacao, String chaveOrigem, String nomeOrigem,
                                       String bancoOrigem, String chaveDestino, String nomeDestino,
                                       BigDecimal valor, LocalDateTime dataTransferencia,
                                       String status, String mensagem, LocalDateTime criadoEm,
                                       LocalDateTime atualizadoEm) {
        this.codigoTransacao = codigoTransacao;
        this.chaveOrigem = chaveOrigem;
        this.nomeOrigem = nomeOrigem;
        this.bancoOrigem = bancoOrigem;
        this.chaveDestino = chaveDestino;
        this.nomeDestino = nomeDestino;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
        this.status = status;
        this.mensagem = mensagem;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    // 🔹 Getters e Setters
    public String getCodigoTransacao() { return codigoTransacao; }
    public void setCodigoTransacao(String codigoTransacao) { this.codigoTransacao = codigoTransacao; }

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

    public LocalDateTime getDataTransferencia() { return dataTransferencia; }
    public void setDataTransferencia(LocalDateTime dataTransferencia) { this.dataTransferencia = dataTransferencia; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
