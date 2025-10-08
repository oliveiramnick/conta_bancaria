package com.example.conta_bancaria.domain.exceptions;

public class ValoresNegativoException extends RuntimeException {
    public ValoresNegativoException(String operacao) {
        super("Não é possível realizar a operação " + operacao + " com valores negativos!");
    }
}
