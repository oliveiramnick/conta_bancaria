package com.example.conta_bancaria.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Conta {
    String idConta;
    String agencia;
    int numero;
    double saldo;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

}
