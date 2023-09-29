package br.com.attornatus.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(IdPessoaInvalidoException.class)
    public ResponseEntity<ViewException> tratarIdPessoaInvalidos(IdPessoaInvalidoException e) {
        return ResponseEntity.badRequest().body(new ViewException(e));
    }

    @ExceptionHandler(IdEnderecoInvalidoException.class)
    public ResponseEntity<ViewException> tratarIdsEnderecoInvalidos(IdEnderecoInvalidoException e) {
        return ResponseEntity.badRequest().body(new ViewException(e));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> tratarErro404(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }



}
