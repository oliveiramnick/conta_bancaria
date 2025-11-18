package com.example.conta_bancaria.domain.exceptions;

public class AutenticacaoIoTExpiradaException extends RuntimeException {
    public AutenticacaoIoTExpiradaException() {
        super("A autentificação do dispositivo IoT foi expirada.");
    }
}