package com.sistemabancario.transferenciapix.exception;

// Importa o DTO que será devolvido como resposta de erro
import com.sistemabancario.transferenciapix.dto.ResponseErroDTO;

// Import necessário para retornar códigos HTTP personalizados
import org.springframework.http.ResponseEntity;

// Exceção disparada quando um DTO anotado com @Valid falha na validação
import org.springframework.web.bind.MethodArgumentNotValidException;

// Anotação que transforma esta classe em um interceptador GLOBAL de erros de Controllers
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice  // Marca esta classe como um "tratador global" de erros das Controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErroDTO> handleGeneric(Exception ex) {

        Map<String, String> detalhes = new HashMap<>();
        detalhes.put("erro", ex.getMessage());

        ResponseErroDTO response = new ResponseErroDTO(
                true,
                "Ocorreu um erro ao processar a requisição.",
                detalhes
        );

        return ResponseEntity.badRequest().body(response);
    }
}
