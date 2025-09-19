package com.example.conta_bancaria.domain.repository;

import com.example.conta_bancaria.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
     Optional<Cliente> findByCpfAndAtivoTrue(String cpf);
}
