package com.sistemabancario.transferenciapix.service;

import com.sistemabancario.transferenciapix.dto.UserDetailRequestTempDTO;
import com.sistemabancario.transferenciapix.entity.UserDetail;
import com.sistemabancario.transferenciapix.repository.UserDetailRepository;
import com.sistemabancario.transferenciapix.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;


    public UserDetailService(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    public UserDetailRequestTempDTO create(UserDetailRequestTempDTO dto) {

        // verificar se já existe detalhe cadastrado
        var exists = userDetailRepository.findByUser_id(dto.userId());
        if (exists.isPresent()) {
            throw new RuntimeException("Detalhe do usuário já cadastrado");
        }
        //Verificar se usuário existe
        var user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //Montar entidade UserDetail
        var userDetail = new UserDetail();
        userDetail.setUser(user);
        userDetail.setEmail(dto.email());
        userDetail.setTelefone(dto.telefone());
        userDetail.setEndereco(dto.endereco());
        userDetail.setCep(dto.cep());
        userDetail.setNumeroResidencia(dto.numeroResidencia());
        userDetail.setTipo(dto.tipo());

        //Salvar no banco
        var savedUserDetail = userDetailRepository.save(userDetail);

        //Retornar DTO
        return new UserDetailRequestTempDTO(
                savedUserDetail.getUser().getId(),
                savedUserDetail.getEmail(),
                savedUserDetail.getTelefone(),
                savedUserDetail.getEndereco(),
                savedUserDetail.getCep(),
                savedUserDetail.getNumeroResidencia(),
                savedUserDetail.getTipo()
        );
    }
}
