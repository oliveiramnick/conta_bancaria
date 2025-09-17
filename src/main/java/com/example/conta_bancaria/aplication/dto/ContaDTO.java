package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.entity.ContaCorrente;
import com.example.conta_bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;

public record ContaDTO(
        String idCliente,
        String idConta,
        BigDecimal saldo
) {
    // Java
    public static ContaDTO fromEntity(Conta conta){
        if (conta == null) return null;
        String idCliente = conta.getCliente() != null ? conta.getCliente().getIdCliente() : null;
        return new ContaDTO(
                idCliente,
                conta.getIdConta(),
                conta.getSaldo()
        );
    }

    public Conta toEntity(Cliente cliente){
        Conta conta;
        conta.setCliente(cliente);
        conta.setIdConta(this.idConta());
        conta.setSaldo(this.saldo());
        return conta;
    }

}
