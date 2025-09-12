package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;

public record ClienteDTO(
        String id,
        String nome,
        Long cpf,
        String idConta
) {
    public static ClienteDTO fromEntity(Cliente cliente){
        if (cliente == null) return null;
        return new ClienteDTO(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getContas() != null ? cliente.getContas().getIdConta() : null
        );
    }
    public Cliente toEntity (Cliente cliente){
        Cliente cliente = new Cliente();

    }
}
