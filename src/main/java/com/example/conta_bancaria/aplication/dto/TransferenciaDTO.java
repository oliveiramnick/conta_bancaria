package com.example.conta_bancaria.aplication.dto;

import java.math.BigDecimal;

public record TransferenciaDTO(
        BigDecimal valor,
        String contaDestino
) {
}
