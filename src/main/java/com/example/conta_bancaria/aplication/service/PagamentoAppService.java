package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.entity.Pagamento;
import com.example.conta_bancaria.domain.entity.Taxa;
import com.example.conta_bancaria.domain.repository.ContaRepository;
import com.example.conta_bancaria.domain.repository.PagamentoRepository;
import com.example.conta_bancaria.domain.repository.TaxaRepository;
import jakarta.transaction.Transactional;

import java.util.NoSuchElementException;

public class PagamentoAppService {
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoDomainService pagamentoDomainService;
    private final ContaRepository contaRepository;
    private final TaxaRepository taxaRepository;

    public PagamentoAppService(
            PagamentoRepository pagamentoRepository,
            PagamentoDomainService pagamentoDomainService,
            ContaRepository contaRepository,
            TaxaRepository taxaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoDomainService = pagamentoDomainService;
        this.contaRepository = contaRepository;
        this.taxaRepository = taxaRepository;
    }

    // Gerentes cadastram taxas
    @Transactional
    public Taxa cadastrarTaxa(Taxa taxa) {
        // Validações básicas de Taxa se necessário
        return taxaRepository.save(taxa);
    }

    // Clientes realizam pagamentos
    @Transactional
    public Pagamento realizarPagamento(Pagamento pagamento) {

        // 1. Pré-validação e busca de dados (Conta, Taxas)
        Conta conta = contaRepository.findById(pagamento.getConta().getIdConta())
                .orElseThrow(() -> new NoSuchElementException("Conta não encontrada."));

        // Garante que o objeto Conta e Taxas está no Pagamento para o Domain Service
        pagamento.setConta(conta);
        // Em um cenário real, as taxas seriam definidas por uma regra de negócio e buscadas aqui.
        // Para a demo, assumimos que o objeto Pagamento já vem com as taxas válidas.

        // 2. Processamento da Regra de Negócio (Débito e Validações)
        Pagamento pagamentoProcessado = pagamentoDomainService.processarPagamento(pagamento);

        // 3. Persistência (Pagamento e Conta)
        Conta contaAtualizada = contaRepository.save(conta); // Persiste o débito
        return pagamentoRepository.save(pagamentoProcessado); // Persiste o Pagamento
    }
}
