package com.example.conta_bancaria.domain.repository;

import com.example.conta_bancaria.domain.entity.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPoupancaRepository  extends JpaRepository<ContaPoupanca, String> {
}
