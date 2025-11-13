package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.domain.entity.Conta;
import com.example.conta_bancaria.domain.entity.Pagamento;
import com.example.conta_bancaria.domain.entity.StatusPagamento;
import com.example.conta_bancaria.domain.entity.Taxa;
import com.example.conta_bancaria.domain.exceptions.PagamentoInvalidoException;
import com.example.conta_bancaria.domain.exceptions.SaldoInsuficienteException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class PagamentoDomainService {
    public BigDecimal calcularValorTotal(Pagamento pagamento) {
        // Variáveis de configuração declaradas localmente
        final int SCALE = 2;
        final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

        BigDecimal valorBase = pagamento.getValorPago();
        // Assumindo que o getter correto seja getTaxas()
        Set<Taxa> taxas = pagamento.getTaxa();
        BigDecimal totalTaxas = BigDecimal.ZERO;

        if (taxas != null && !taxas.isEmpty()) {
            for (Taxa taxa : taxas) {
                BigDecimal valorTaxaPercentual = BigDecimal.ZERO;

                // 1. Cálculo da Taxa Percentual
                if (taxa.getPercentual() != null && taxa.getPercentual().compareTo(BigDecimal.ZERO) > 0) {
                    valorTaxaPercentual = valorBase
                            .multiply(taxa.getPercentual())
                            .setScale(SCALE, ROUNDING_MODE);
                }

                // 2. Cálculo da Taxa Fixa
                // Se getValorFixo() for null, usa BigDecimal.ZERO
                BigDecimal valorTaxaFixa = taxa.getValorFixo() != null ?
                        taxa.getValorFixo().setScale(SCALE, ROUNDING_MODE) :
                        BigDecimal.ZERO;

                // 3. Soma
                totalTaxas = totalTaxas.add(valorTaxaPercentual).add(valorTaxaFixa);
            }
        }

        // Retorna o valor final arredondado
        return valorBase.add(totalTaxas).setScale(SCALE, ROUNDING_MODE);
    }

    /**
     * Processa a transação, realizando as validações e o débito na conta.
     * @param pagamento O objeto Pagamento a ser processado.
     * @return O Pagamento atualizado com status SUCESSO.
     */
    public Pagamento processarPagamento(Pagamento pagamento) {

        // 1. Calcular o valor total (incluindo taxas)
        BigDecimal valorTotal = calcularValorTotal(pagamento);

        Conta conta = pagamento.getConta();

        // 2. Validação de Vencimento do Boleto (Regra de Exemplo)
        // Aqui, apenas validamos se a data do pagamento é no futuro, o que é inválido.
        if (pagamento.getDataPagamento() != null && pagamento.getDataPagamento().isAfter(LocalDateTime.now())) {
            pagamento.setStatus(StatusPagamento.FALHA);
            throw new PagamentoInvalidoException("Data de pagamento inválida.");
        }

        // 3. Validação de Saldo Insuficiente
        if (conta.getSaldo().compareTo(valorTotal) < 0) {
            pagamento.setStatus(StatusPagamento.SALDO_INSUFICIENTE);
            throw new SaldoInsuficienteException(); // Adicionei mensagem de erro
        }

        // 4. Débito e Conclusão
        // Assumindo que a entidade Conta possui o método debitar(BigDecimal valor)
        conta.debitar(valorTotal);

        pagamento.setStatus(StatusPagamento.SUCESSO);
        pagamento.setDataPagamento(LocalDateTime.now());

        return pagamento;
    }
   }