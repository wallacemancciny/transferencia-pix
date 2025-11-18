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

    // Diz para o Spring: "Quando acontecer MethodArgumentNotValidException, chame este método"
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErroDTO> handleValidation(MethodArgumentNotValidException ex) {

        // Estrutura que vai guardar os erros no formato campo → mensagem
        Map<String, String> erros = new HashMap<>();

        // Pega todos os erros de validação que aconteceram no DTO
        // Para cada erro:
        //   - error.getField() pega o nome do campo
        //   - error.getDefaultMessage() pega a mensagem definida no @NotBlank, @Email etc
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> erros.put(error.getField(), error.getDefaultMessage()));

        // Monta o objeto final de resposta, seguindo seu padrão:
        // erro = true
        // msg = mensagem principal da API
        // detalhes = mapa contendo campo → mensagem
        ResponseErroDTO response = new ResponseErroDTO(
                true,
                "Erro de validação nos campos enviados.",
                erros
        );

        // Retorna a resposta com status 400 (Bad Request)
        // e o corpo contendo o objeto ResponseErroDTO já preenchido
        return ResponseEntity.badRequest().body(response);
    }
}
