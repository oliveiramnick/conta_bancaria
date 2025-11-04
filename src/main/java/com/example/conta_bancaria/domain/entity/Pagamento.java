package com.example.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@SuperBuilder
@Table(name="pagamento")
@DiscriminatorValue("PAGAMENTO")

public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String boleto;


    @Column(nullable = false, unique = true)
    private LocalDateTime dataPagamento;

    @Column(nullable = false)
    private Double valorPago;

    @Column(nullable = false)
    private StatusPagamento status;



    // Associação com a Conta
    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    // Associação ManyToOne com Taxa
    @ManyToOne
    @JoinColumn(name = "taxa_id", nullable = true)
    private Taxa taxa;


    // Métodos de lógica de pagamento podem ser adicionados aqui
}