package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.aplication.dto.ClienteDTO;
import com.example.conta_bancaria.domain.repository.ClienteRepository;
import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ContaRepository contaRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::fromEntity)
                .orElse(null);
    }
    @Transactional(readOnly = true)
    public ClienteDTO buscarClientePorId(String id) {
        return clienteRepository.findById(id)
                .map(ClienteDTO::fromEntity)
                .orElse(null);
    }
    public ClienteDTO salvarCliente(ClienteDTO dto) {
        Optional<Conta> contaOpt = contaRepository.findById(dto.idCliente());
        Cliente entidade = dto.toEntity(contaOpt.get());
        Cliente salvo = clienteRepository.save(entidade);
        return ClienteDTO.fromEntity(salvo);
    }
    public ClienteDTO atualizarCliente(String id, ClienteDTO dto){
        Optional<Cliente> clienteExistenteOpt = clienteRepository.findById(id);

        Cliente existente = clienteExistenteOpt.get();
        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());
        existente.setContas(dto.contas());

    }
}
