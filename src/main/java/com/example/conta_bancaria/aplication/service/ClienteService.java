package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.aplication.dto.ClienteRegistroDTO;
import com.example.conta_bancaria.aplication.dto.ClienteResponseDTO;
import com.example.conta_bancaria.domain.repository.ClienteRepository;
import com.example.conta_bancaria.domain.entity.Cliente;
import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteResponseDTO registrarClienteOuAnexarConta(ClienteRegistroDTO dto){
        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity())
        );
        return null;
    }
    var conta


    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ContaRepository contaRepository;

    @Transactional(readOnly = true)
    public List<ClienteRegistroDTO> listarClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(ClienteRegistroDTO::fromEntity)
                .toList();
    }
    @Transactional(readOnly = true)
    public ClienteRegistroDTO buscarClientePorId(String id) {
        return clienteRepository.findById(id)
                .map(ClienteRegistroDTO::fromEntity)
                .orElse(null);
    }
    public ClienteRegistroDTO salvarCliente(ClienteRegistroDTO dto) {
        Optional<Conta> contaOpt = contaRepository.findById(dto.idCliente());
        Cliente entidade = dto.toEntity(contaOpt.get());
        Cliente salvo = clienteRepository.save(entidade);
        return ClienteRegistroDTO.fromEntity(salvo);
    }
    public ClienteRegistroDTO atualizarCliente(String id, ClienteRegistroDTO dto){
        Optional<Cliente> clienteExistenteOpt = clienteRepository.findById(id);

        Cliente existente = clienteExistenteOpt.get();
        existente.setIdCliente(dto.idCliente());
        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());

        if (dto.idConta() != null) {
            Optional<Conta> contaOpt = contaRepository.findById(dto.idConta());
            contaOpt.ifPresent(conta -> existente.setContas(List.of(conta)));
        } else {
            existente.setContas(null);
        }
        Cliente atualizado = clienteRepository.save(existente);
        return ClienteRegistroDTO.fromEntity(atualizado);
    }
    public void deletarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}
