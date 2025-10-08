package com.example.conta_bancaria.domain.exceptions;

public class ContaMesmoTipoException extends RuntimeException {
    public ContaMesmoTipoException() {
        super("O cliente já possui uma conta do mesmo tipo.");
    }
}
