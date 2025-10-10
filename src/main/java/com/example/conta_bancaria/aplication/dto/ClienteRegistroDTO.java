package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


public record ClienteRegistroDTO(
        @NotBlank
        String nome,
        @NotNull
        String cpf,
        @NotBlank
        ContaResumoDTO contaDTO
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
