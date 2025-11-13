package com.example.conta_bancaria.domain.repository;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, String> {
}
