package com.example.conta_bancaria.domain.entity;


import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cliente {
    String nome;
    Long cpf;
    List<Conta> contas;
}
