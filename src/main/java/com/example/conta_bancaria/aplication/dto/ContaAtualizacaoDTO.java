package com.example.conta_bancaria.aplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaAtualizacaoDTO (
        @NotNull
        BigDecimal saldo,
        @NotNull
        BigDecimal limite,
        @NotNull
        BigDecimal rendimento,
        @NotNull
        BigDecimal taxa
){

}
