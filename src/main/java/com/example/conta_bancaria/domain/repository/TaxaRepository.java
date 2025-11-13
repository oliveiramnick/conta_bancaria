package com.example.conta_bancaria.domain.repository;
import com.example.conta_bancaria.domain.entity.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxaRepository extends JpaRepository<Taxa, String> {
}
