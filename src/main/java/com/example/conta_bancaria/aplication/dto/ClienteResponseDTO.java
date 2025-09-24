package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;

import java.util.List;

public record ClienteResponseDTO(
        String id,
        String nome,
        String cpf,
        List<ContaResumoDTO> contas
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        List<ContaResumoDTO> contas = cliente.getContas().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getCpf(),
                contas
        );
    }
}
