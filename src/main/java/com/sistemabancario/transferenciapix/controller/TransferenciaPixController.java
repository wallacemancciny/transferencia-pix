package com.sistemabancario.transferenciapix.controller;

import com.sistemabancario.transferenciapix.dto.OutputSuccessDTO;
import com.sistemabancario.transferenciapix.dto.TransferenciaPixRequestDTO;
import com.sistemabancario.transferenciapix.dto.TransferenciaPixResponseDTO;
import com.sistemabancario.transferenciapix.service.TransferenciaPixService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Indica que esta classe é um controller REST (retorna JSON)
@RequestMapping("/api/transferencias") // Define o "prefixo" para todos os endpoints dessa API
public class TransferenciaPixController {

    // O controller depende do service (injeção automática via construtor)
    private final TransferenciaPixService service;

    public TransferenciaPixController(TransferenciaPixService service) {
        this.service = service;
    }

    @PostMapping // Indica que é um endpoint HTTP POST
    public ResponseEntity<OutputSuccessDTO> enviar(@Valid @RequestBody TransferenciaPixRequestDTO transferencia) {
        // O JSON da requisição é convertido em um objeto TransferenciaPix automaticamente pelo Spring

        TransferenciaPixResponseDTO dto = service.enviar(transferencia);

        return ResponseEntity.ok(new OutputSuccessDTO(dto));
    }

    @GetMapping
    public ResponseEntity<OutputSuccessDTO<List<TransferenciaPixResponseDTO>>> listar() {

        List<TransferenciaPixResponseDTO> dtoList = service.listar();

        return ResponseEntity.ok(new OutputSuccessDTO(dtoList));

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<OutputSuccessDTO> buscarPorCodigo(@PathVariable String codigo) {
        // @PathVariable = captura o valor da URL e passa pro método
        TransferenciaPixResponseDTO dto = service.buscarPorCodigo(codigo);

        return ResponseEntity.ok(new OutputSuccessDTO(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<OutputSuccessDTO> cancelarPixPorCodigo(@PathVariable String codigo) {

        TransferenciaPixResponseDTO dto = service.cancelarPixPorCodigo(codigo);

        return ResponseEntity.ok(new OutputSuccessDTO(dto));
    }

    @DeleteMapping("/{codigotransacao}")
    public ResponseEntity<OutputSuccessDTO> deletarPorCodigoTransacao(@PathVariable String codigotransacao) {
        try {
            service.deletarPorCodigoTransacao(codigotransacao);

            ResponseEntity<OutputSuccessDTO> ok = ResponseEntity.ok(new OutputSuccessDTO("Transferencia " + codigotransacao + " deletada com sucesso!"));
            return ok;

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OutputSuccessDTO("Erro ao deletar transferência " + codigotransacao + " | " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new OutputSuccessDTO("Erro inesperado ao deletar transferência " + codigotransacao + " | " + e.getMessage()));
        }

    }
}
