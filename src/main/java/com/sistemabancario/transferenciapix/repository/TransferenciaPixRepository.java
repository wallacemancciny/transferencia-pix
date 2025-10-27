package com.sistemabancario.transferenciapix.repository;

import com.sistemabancario.transferenciapix.entity.TransferenciaPix;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransferenciaPixRepository extends JpaRepository<TransferenciaPix, Long> {
    Optional<TransferenciaPix> findByCodigoTransacao(String codigoTransacao);

    // 🔹 Deletar por código
    void deleteByCodigoTransacao(String codigoTransacao);
}
