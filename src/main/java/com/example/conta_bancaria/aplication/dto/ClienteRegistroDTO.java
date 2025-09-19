package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;

import java.util.ArrayList;
import java.util.List;


public record ClienteRegistroDTO(
        String nome,
        String cpf,
        ContaResumoDTO contaDTO
)
{
    public static ClienteRegistroDTO fromEntity(Cliente cliente){
        if (cliente == null) return null;
        return new ClienteRegistroDTO(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getContas() != null ? cliente.getContas().getFirst().getIdConta() : null
        );
    }
    public Cliente toEntity (){
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<Conta>())
                .build();
    }
}
