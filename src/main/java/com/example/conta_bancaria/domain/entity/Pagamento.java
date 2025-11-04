package com.example.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conta conta;

    @Column(nullable = false)
    private String boleto;

    @Column(nullable = false)
    private BigDecimal valorPago;

    @Column(nullable = false)
    private String dataPagamento;

    @Column(nullable = false)
    private StatusPagamento staus;

    @ManyToMany(fetch = FetchType.LAZY)
    private Taxa taxas;

}
