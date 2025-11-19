package com.sistemabancario.transferenciapix.controller;

import com.sistemabancario.transferenciapix.dto.OutputSuccessDTO;
import com.sistemabancario.transferenciapix.dto.TransferenciaPixRequestDTO;
import com.sistemabancario.transferenciapix.dto.TransferenciaPixResponseDTO;
import com.sistemabancario.transferenciapix.entity.TransferenciaPix;
import com.sistemabancario.transferenciapix.service.TransferenciaPixService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

/**
 * Classe responsável por expor os endpoints da API de Transferência Pix.
 * Ela recebe as requisições HTTP (via JSON), chama o Service e retorna a resposta.
 */
@RestController // Indica que esta classe é um controller REST (retorna JSON)
@RequestMapping("/api/transferencias") // Define o "prefixo" para todos os endpoints dessa API
public class TransferenciaPixController {

    // O controller depende do service (injeção automática via construtor)
    private final TransferenciaPixService service;

    public TransferenciaPixController(TransferenciaPixService service) {
        this.service = service;
    }

    /**
     * Endpoint responsável por enviar uma nova transferência Pix.
     * Método POST → cria um novo registro.
     *
     * Exemplo de chamada:
     * POST /api/transferencias
     * {
     *   "chaveOrigem": "wallace@banco.com",
     *   "nomeOrigem": "Wallace Santana",
     *   "bancoOrigem": "Banco XPTO",
     *   "chaveDestino": "joao@banco.com",
     *   "nomeDestino": "João Almeida",
     *   "valor": 250.75,
     *   "mensagem": "Pagamento de serviço"
     * }
     */
    @PostMapping // Indica que é um endpoint HTTP POST
    public ResponseEntity<OutputSuccessDTO> enviar(@Valid @RequestBody TransferenciaPixRequestDTO transferencia) {
        // O JSON da requisição é convertido em um objeto TransferenciaPix automaticamente pelo Spring

        TransferenciaPixResponseDTO dto = service.enviar(transferencia);

        OutputSuccessDTO response = new OutputSuccessDTO(
                true,
                dto,
                Instant.now().toString()
        );

        return ResponseEntity.ok(response);
    }
    /**
     * Endpoint responsável por listar todas as transferências Pix cadastradas.
     * Método GET → retorna todos os registros.
     *
     * Exemplo:
     * GET /api/transferencias
     */
    @GetMapping
    public List<TransferenciaPix> listar() {
        return service.listar();
    }

    /**
     * Endpoint responsável por buscar uma transferência específica pelo código Pix.
     * Método GET → busca um registro único.
     *
     * Exemplo:
     * GET /api/transferencias/PIX-A1B2C3D4
     */
    @GetMapping("/{codigo}")
    public TransferenciaPix buscarPorCodigo(@PathVariable String codigo) {
        // @PathVariable = captura o valor da URL e passa pro método
        return service.buscarPorCodigo(codigo);
    }

    @PutMapping("/{codigo}")
    public TransferenciaPix cancelarPixPorCodigo(@PathVariable String codigo) {
        return service.cancelarPixPorCodigo(codigo);
    }

    @DeleteMapping("/{codigotransacao}")
    public ResponseEntity<String> deletarPorCodigoTransacao(@PathVariable String codigotransacao) {
        try {
            service.deletarPorCodigoTransacao(codigotransacao);
            return ResponseEntity.ok("Transferência deletada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar transferência: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado ao deletar transferência");
        }

    }
}
