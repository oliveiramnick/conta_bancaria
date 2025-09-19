package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.aplication.dto.ContaDTO;
import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.repository.ClienteRepository;
import com.example.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContaService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ContaRepository contaRepository;

    @Transactional(readOnly = true)
    public List<ContaDTO> listarContas(){
        return contaRepository.findAll()
                .stream()
                .map(ContaDTO::fromEntity)
                .toList();
    }
    @Transactional(readOnly = true)
    public ContaDTO buscarContaPorId(String id) {
        return contaRepository.findById(id)
                .map(ContaDTO::fromEntity)
                .orElse(null);
    }
    public ContaDTO salvarConta(ContaDTO dto) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(dto.idCliente());
        Conta entidade = dto.toEntity(clienteOpt.get());
        Conta salva = contaRepository.save(entidade);
        return ContaDTO.fromEntity(salva);
    }
    public ContaDTO atualizarConta(String id, ContaDTO dto){
        Optional<Conta> contaExistenteOpt = contaRepository.findById(id);

        Conta existente = contaExistenteOpt.get();
        existente.setIdConta(dto.idConta());
        existente.setSaldo(dto.saldo());

        if (dto.idConta() != null) {
            Optional<Cliente> clienteOpt = clienteRepository.findById(dto.idCliente());
            clienteOpt.ifPresent(cliente -> existente.setCliente(cliente));
        } else {
            existente.setCliente(null);
        }
        Conta atualizado = contaRepository.save(existente);
        return ContaDTO.fromEntity(atualizado);
    }
    public void deletarConta(String id) {
        contaRepository.deleteById(id);
    }
}
