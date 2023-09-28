package br.com.attornatus.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(IdPessoaInvalidoException.class)
    public ResponseEntity<ViewException> tratarIdPessoaInvalidos() {
        return ResponseEntity.badRequest().body(new ViewException("id da pessoa inválido"));
    }

    @ExceptionHandler(IdEnderecoInvalidoException.class)
    public ResponseEntity<ViewException> tratarIdsEnderecoInvalidos() {
        return ResponseEntity.badRequest().body(new ViewException("id do endereço inválido"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> tratarErro404() {
        return ResponseEntity.notFound().build();
    }



}
