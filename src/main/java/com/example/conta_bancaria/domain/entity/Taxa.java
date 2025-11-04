package com.example.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Taxa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idTaxa;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal percentual;

    @Column(nullable = false)
    private int valorFixo;

}
