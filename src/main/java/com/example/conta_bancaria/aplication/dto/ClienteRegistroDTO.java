package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.enums.Role;
import org.hibernate.validator.constraints.br.CPF; // Importa a anotação @CPF
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.ArrayList;


public record ClienteRegistroDTO(
        @NotBlank
        String nome,
        @NotNull
        String cpf,
        @NotNull
        String email,
        @NotNull
        String senha,
        @NotNull
        ContaResumoDTO contaDTO,
        Role role
)
{
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .senha(this.senha)
                .role(Role.CLIENTE)
                .contas(new ArrayList<>())
                .build();
    }
}
