package com.sistemabancario.transferenciapix.mapper;

import com.sistemabancario.transferenciapix.dto.TransferenciaPixRequestDTO;
import com.sistemabancario.transferenciapix.dto.TransferenciaPixResponseDTO;
import com.sistemabancario.transferenciapix.entity.TransferenciaPix;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferenciaPixMapper {
    // 🔹 O MapStruct vai gerar automaticamente a classe TransferenciaPixMapperImpl
    // 🔹 O Spring vai gerenciar essa instância, então você injeta via @Autowired

    TransferenciaPix toEntity(TransferenciaPixRequestDTO dto);

    TransferenciaPixResponseDTO toResponseDTO(TransferenciaPix entity);

}
