package com.sistemabancario.transferenciapix.dto;

public class OutputSuccessDTO {

    private boolean success;
    private TransferenciaPixResponseDTO data;
    private String timestamp;

    public boolean isSuccess() {
        return success;
    }

    public TransferenciaPixResponseDTO getData() {
        return data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setData(TransferenciaPixResponseDTO data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OutputSuccessDTO(boolean success, TransferenciaPixResponseDTO data, String timestamp) {

        this.success = success;
        this.data = data;
        this.timestamp = timestamp;
    }
}

