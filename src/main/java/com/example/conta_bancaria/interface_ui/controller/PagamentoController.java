package com.example.conta_bancaria.interface_ui.controller;

import com.example.conta_bancaria.aplication.service.PagamentoAppService;
import com.example.conta_bancaria.domain.entity.Pagamento;
import com.example.conta_bancaria.domain.entity.Taxa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PagamentoController {
    private final PagamentoAppService pagamentoAppService;

    public PagamentoController(PagamentoAppService pagamentoAppService) {
        this.pagamentoAppService = pagamentoAppService;
    }

    /**
     * Endpoint para Clientes realizarem pagamentos.
     * @param pagamento Pagamento com conta, valor e taxas.
     */
    @PostMapping("/pagamentos")
    @PreAuthorize("hasRole('CLIENTE')") // Apenas Clientes podem pagar
    public ResponseEntity<Pagamento> realizarPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novoPagamento = pagamentoAppService.realizarPagamento(pagamento);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    /**
     * Endpoint para Gerentes cadastrarem novas taxas.
     * @param taxa Detalhes da Taxa (descrição, percentual, valorFixo).
     */
    @PostMapping("/taxas")
    @PreAuthorize("hasRole('GERENTE')") // Apenas Gerentes podem cadastrar taxas
    public ResponseEntity<Taxa> cadastrarTaxa(@RequestBody Taxa taxa) {
        Taxa novaTaxa = pagamentoAppService.cadastrarTaxa(taxa);
        return new ResponseEntity<>(novaTaxa, HttpStatus.CREATED);
    }
}
