package com.example.conta_bancaria.domain.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String message) {

        super(message);
    }
}
