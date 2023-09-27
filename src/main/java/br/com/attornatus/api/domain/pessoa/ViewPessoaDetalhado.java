package br.com.attornatus.api.domain.pessoa;

import br.com.attornatus.api.domain.endereco.ViewEndereco;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record ViewPessoaDetalhado(
        Long id,
        String nome,
        @JsonFormat (pattern = "dd-MM-yyyy")
        LocalDate dataNascimento,
        List<ViewEndereco> enderecos
){
    public ViewPessoaDetalhado(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getEnderecos().stream().map(ViewEndereco::new).toList());
    }
}