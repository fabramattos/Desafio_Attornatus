package br.com.attornatus.api.infra.exceptions;

public record ViewException(String mensagem) {
    public ViewException(RuntimeException e) {
        this(e.getMessage());
    }
}
