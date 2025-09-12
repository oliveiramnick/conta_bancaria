package com.example.conta_bancaria.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String idCliente;

    String nome;
    Long cpf;

    @OneToMany(mappedBy = "conta")
    List<Conta> contas;
}
