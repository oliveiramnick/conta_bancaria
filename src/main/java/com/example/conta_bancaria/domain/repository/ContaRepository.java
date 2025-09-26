package com.example.conta_bancaria.domain.repository;

import com.example.conta_bancaria.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, String> {

    List<Conta> findAllByAtivaTrue();
    Optional<Conta> findByNumeroAndAtivaTrue(String numero);

}
