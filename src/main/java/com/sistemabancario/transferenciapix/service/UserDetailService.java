package com.sistemabancario.transferenciapix.service;

import com.sistemabancario.transferenciapix.dto.*;
import com.sistemabancario.transferenciapix.entity.UserDetail;
import com.sistemabancario.transferenciapix.repository.UserDetailRepository;
import com.sistemabancario.transferenciapix.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService {

    private final WebClient webClient;

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;


    public UserDetailService(WebClient webClient, UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.webClient = webClient;
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    public List<UserResponseDTO> getUserByCity(String city) {

        List<UserDetail> userDetails = userDetailRepository.findByCidade(city);

        return userDetails.stream()
                .map(ud -> new UserResponseDTO(
                        ud.getUser(),
                        ud.getCep(),
                        ud.getEmail(),
                        ud.getTelefone(),
                        ud.getEndereco(),
                        ud.getCidade(),
                        ud.getBairro(),
                        ud.getNumeroResidencia(),
                        ud.getTipo()
                )).toList();
    }

    public UserDetailResponseDTO create(UserDetailRequestDTO dto) {

        // verificar se já existe detalhe cadastrado
        var exists = userDetailRepository.findByUser_id(dto.userId());
        if (exists.isPresent()) {
            throw new UsernameNotFoundException("Detalhe do usuário já cadastrado");
        }
        //Verificar se usuário existe
        var user = userRepository.findById(dto.userId())
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        //Conectar na api dos correios para buscar os dados de endereço
        EnderecoResponseDTO getEndereco = webClient.get()
                .uri("https://viacep.com.br/ws/{cep}/json/", dto.cep())
                .retrieve()
                .bodyToMono(EnderecoResponseDTO.class)
                .block();

        //Montar entidade UserDetail
        var userDetail = new UserDetail();
        userDetail.setUser(user);
        userDetail.setCep(getEndereco.cep());
        userDetail.setEmail(dto.email());
        userDetail.setTelefone(dto.telefone());
        userDetail.setEndereco(getEndereco.logradouro());
        userDetail.setCidade(getEndereco.localidade());
        userDetail.setBairro(getEndereco.bairro());
        userDetail.setNumeroResidencia(dto.numeroResidencia());
        userDetail.setTipo(dto.tipo());

        //Salvar no banco
        var savedUserDetail = userDetailRepository.save(userDetail);

        //Retornar DTO
        return new UserDetailResponseDTO(
                savedUserDetail.getCep(),
                savedUserDetail.getEmail(),
                savedUserDetail.getTelefone(),
                savedUserDetail.getEndereco(),
                savedUserDetail.getCidade(),
                savedUserDetail.getBairro(),
                savedUserDetail.getNumeroResidencia(),
                savedUserDetail.getTipo()
        );
    }
}
