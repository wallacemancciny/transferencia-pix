package com.sistemabancario.transferenciapix.dto;

import java.util.Map;

public class ResponseErroDTO {

    private boolean erro;
    private String msg;
    private Map<String, String> detalhes;

    public ResponseErroDTO() {}

    public ResponseErroDTO(boolean erro, String msg, Map<String, String> detalhes) {
        this.erro = erro;
        this.msg = msg;
        this.detalhes = detalhes;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(Map<String, String> detalhes) {
        this.detalhes = detalhes;
    }
}
