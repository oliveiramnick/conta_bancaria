package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;

public record ContaDTO(
        String idCliente,
        String idConta,
        Double saldo
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
        Conta conta = new Conta();
        conta.setCliente(cliente);
        conta.setIdConta(this.idConta());
        conta.setSaldo(this.saldo());
        return conta;
    }

}
