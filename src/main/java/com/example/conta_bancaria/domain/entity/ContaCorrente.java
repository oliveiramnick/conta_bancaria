package com.example.conta_bancaria.domain.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class ContaCorrente extends Conta {
    Long limite;
    double taxa;
}
