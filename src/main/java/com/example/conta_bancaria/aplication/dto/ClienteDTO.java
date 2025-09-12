package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;

import java.util.List;


public record ClienteDTO(
        String idCliente,
        String nome,
        Long cpf,
        String idConta
)
{
    public static ClienteDTO fromEntity(Cliente cliente){
        if (cliente == null) return null;
        return new ClienteDTO(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getContas() != null ? cliente.getContas().getFirst().getIdConta() : null
        );
    }
    public Cliente toEntity (Conta conta){
        Cliente cliente = new Cliente();
        cliente.setIdCliente(this.idCliente);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setContas((List<Conta>) conta);
        return cliente;
    }
}
