package br.com.attornatus.api.infra.exceptions;

import lombok.Getter;

public class IdPessoaInvalidoException extends RuntimeException{

    @Getter
    private static final String mensagem = "'id' da pessoa inválido";

    public IdPessoaInvalidoException() {
        super(mensagem);
    }
}

