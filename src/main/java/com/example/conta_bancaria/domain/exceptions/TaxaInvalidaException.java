package com.example.conta_bancaria.domain.exceptions;

public class TaxaInvalidaException extends RuntimeException {
    public TaxaInvalidaException(String message) {
        super(message);
    }
}
