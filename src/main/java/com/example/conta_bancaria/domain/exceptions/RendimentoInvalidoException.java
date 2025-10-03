package com.example.conta_bancaria.domain.exceptions;

public class RendimentoInvalidoException extends RuntimeException {
    public RendimentoInvalidoException() {
        super("Rendimento deve ser aplicado somente em conta poupança.");
    }
}
