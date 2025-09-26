package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.aplication.dto.ContaAtualizacaoDTO;
import com.example.conta_bancaria.aplication.dto.ContaResumoDTO;
import com.example.conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository repository;

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> listarTodasContas() {
        return repository.findAllByAtivaTrue().stream()
                .map(ContaResumoDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO buscarContaPorNumero(String numero) {
        return ContaResumoDTO.fromEntity(
                repository.findByNumeroAndAtivaTrue(numero)
                        .orElseThrow(() -> new RuntimeException("Conta não encontrada"))
        );
    }
    public ContaResumoDTO atualizarConta(String numeroDaConta, ContaAtualizacaoDTO dto){
        var conta = repository.findByNumeroAndAtivaTrue(numeroDaConta).orElseThrow(
                () -> new RuntimeException("Conta não encontrada.")
        );
        conta.setSaldo(dto.saldo());

        if (conta instanceof )
    }
}
