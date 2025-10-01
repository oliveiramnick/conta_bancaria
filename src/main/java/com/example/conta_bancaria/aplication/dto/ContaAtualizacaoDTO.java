package com.example.conta_bancaria.aplication.dto;

import java.math.BigDecimal;

public record ContaAtualizacaoDTO (
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal rendimento,
        BigDecimal taxa
){

}
