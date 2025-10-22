package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Role;

import java.util.ArrayList;


public record ClienteRegistroDTO(
        @NotBlank
        String nome,
        @NotNull
        String cpf,
        @NotBlank
        ContaResumoDTO contaDTO,
        Role role
)
{
    public Cliente toEntity (){
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<Conta>())
                .build();
    }
}
