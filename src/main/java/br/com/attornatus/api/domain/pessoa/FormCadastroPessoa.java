package br.com.attornatus.api.domain.pessoa;

import br.com.attornatus.api.domain.endereco.FormCadastroEndereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FormCadastroPessoa(

    @NotBlank
    String nome,
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dataNascimento,
    @NotNull @Valid
    FormCadastroEndereco endereco
){}