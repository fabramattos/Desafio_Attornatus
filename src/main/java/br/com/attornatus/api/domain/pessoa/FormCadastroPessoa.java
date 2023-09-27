package br.com.attornatus.api.domain.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record FormCadastroPessoa(
    @NotBlank
    String nome,
    @NotBlank @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dataNascimento
){}