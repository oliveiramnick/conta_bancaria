package com.example.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Entity
@Data
public class Pagamento {
    @Id
    private String idPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conta conta;

    private String boleto;

    private BigDecimal valorPago;
    private String dataPagamento;

    private String staus;

}
