package br.com.attornatus.api.infra.exceptions;

import lombok.Getter;

public class IdEnderecoInvalidoException extends RuntimeException {

    @Getter
    private static final String mensagem = "'id' do endereço inválido";

    public IdEnderecoInvalidoException() {
        super(mensagem);
    }
}
