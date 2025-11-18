package com.example.conta_bancaria.domain.exceptions;

public class DispositivoNaoEncontradoException extends RuntimeException {
    public DispositivoNaoEncontradoException() {
        super("Nenhum dispositivo IoT ativo foi encontrado para o cliente.");
    }
}
