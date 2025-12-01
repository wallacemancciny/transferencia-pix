package com.sistemabancario.transferenciapix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_detail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "telefone", nullable = false)
    private String telefone;
    @Column(name = "endereco", nullable = false)
    private String endereco;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "numero_residencia")
    private Integer numeroResidencia;
    @Column(name = "tipo")
    private String tipo;
}
