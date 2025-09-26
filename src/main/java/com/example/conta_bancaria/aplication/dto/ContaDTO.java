package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.entity.ContaCorrente;
import com.example.conta_bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;
import java.util.ArrayList;

public record ContaDTO(
        String idCliente,
        String idConta,
        BigDecimal saldo,
        BigDecimal rendimento,
        BigDecimal taxa,
        String limite
) {

    public Conta toEntity (){
        return Conta.builder()
                .idCliente
                .idConta
                .saldo
                .rendimento
    }

}
