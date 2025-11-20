package com.sistemabancario.transferenciapix.service;

// Importa a entidade (modelo da tabela) e o repositório (interface que acessa o banco)
import com.sistemabancario.transferenciapix.dto.TransferenciaPixRequestDTO;
import com.sistemabancario.transferenciapix.dto.TransferenciaPixResponseDTO;
import com.sistemabancario.transferenciapix.entity.TransferenciaPix;
import com.sistemabancario.transferenciapix.mapper.TransferenciaPixMapper;
import com.sistemabancario.transferenciapix.repository.TransferenciaPixRepository;

// Importa as anotações e utilitários necessários
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço responsável por aplicar regras de negócio
 * e intermediar a comunicação entre o Controller (camada web)
 * e o Repository (camada de acesso ao banco).
 */
@Service // Indica ao Spring que esta classe é um "Service" — ela será gerenciada automaticamente (injeção de dependência)
public class TransferenciaPixService {

    // Repositório usado para acessar o banco de dados (tabela transferencia_pix)
    private final TransferenciaPixRepository repository;
    //mapper
    private final TransferenciaPixMapper mapper;

    /**
     * Construtor que recebe o repository.
     * O Spring injeta automaticamente a dependência (injeção via construtor).
     */
    // ✅ Injeção via construtor (boa prática)
    public TransferenciaPixService(TransferenciaPixRepository repository, TransferenciaPixMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Método responsável por criar/enviar uma nova transferência Pix.
     * Aqui é onde aplicamos as regras de negócio antes de salvar no banco.
     */
    public TransferenciaPixResponseDTO enviar(TransferenciaPixRequestDTO transferenciaPixRequestDTO) {
        // 🔸 Validação: impede que uma transferência com valor nulo ou negativo seja criada
        if (transferenciaPixRequestDTO.getValor() == null || transferenciaPixRequestDTO.getValor().doubleValue() <= 0) {
            throw new IllegalArgumentException("Valor inválido para transferência Pix");
        }

        // Converte DTO - Entidade
        TransferenciaPix transferencia = mapper.toEntity(transferenciaPixRequestDTO);

        // 🔸 Geração do código único da transação Pix
        // Cria algo como: PIX-A1B2C3D4
        transferencia.setCodigoTransacao("PIX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        // 🔸 Define o status inicial da transferência como "PENDENTE"
        transferencia.setStatus("CONFIRMADO");

        // 🔸 Salva o objeto no banco de dados e retorna a entidade já persistida
        // O repository usa o JPA para gerar automaticamente o comando SQL INSERT

        // 🔸 Salva a entidade e obtém o objeto persistido
        TransferenciaPix saved = repository.save(transferencia);

        // 🔸 Cria e retorna o DTO de resposta (sem ID)
        return mapper.toResponseDTO(saved);
    }

    /**
     * Método para listar todas as transferências Pix do banco de dados.
     * Ideal para endpoints de consulta ou listagem geral.
     */
    public List<TransferenciaPixResponseDTO> listar() {
        // 🔸 Usa o método padrão do JpaRepository (findAll)
        // Internamente, executa: SELECT * FROM transferencia_pix
        List<TransferenciaPix> transferenciaPixList = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return transferenciaPixList.stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    /**
     * Método para buscar uma transferência específica pelo código Pix.
     * É usado, por exemplo, quando o usuário quer rastrear um Pix pelo código do comprovante.
     */
    public TransferenciaPixResponseDTO buscarPorCodigo(String codigo) {
        // 🔸 Chama o método personalizado do repository: findByCodigoTransacao
        // 🔸 Caso não encontre, lança uma exceção com a mensagem "Transferência não encontrada"

        TransferenciaPix transferenciaPix = repository.findByCodigoTransacao(codigo)
                .orElseThrow(() -> new RuntimeException("Transferencia não encontrada"));

        return mapper.toResponseDTO(transferenciaPix);

    }

    public TransferenciaPixResponseDTO cancelarPixPorCodigo(String codigo) {
        // Busca a transferência
        TransferenciaPix transferencia = repository.findByCodigoTransacao(codigo)
                .orElseThrow(() -> new RuntimeException("Transferência não encontrada"));

        // Altera o status
        transferencia.setStatus("CANCELADO");
        //Converte

        // Atualiza no banco de dados
        TransferenciaPix saved = repository.save(transferencia);

        return mapper.toResponseDTO(saved);

    }

    @Transactional
    public void deletarPorCodigoTransacao(String codigo) {
        if (repository.findByCodigoTransacao(codigo).isEmpty()) {
            throw new RuntimeException("Transferência não encontrada para exclusão");
        }

        repository.deleteByCodigoTransacao(codigo);
    }

}