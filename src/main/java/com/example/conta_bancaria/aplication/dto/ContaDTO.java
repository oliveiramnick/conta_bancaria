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



}
