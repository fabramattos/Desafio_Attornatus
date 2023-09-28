package br.com.attornatus.api.service;

import br.com.attornatus.api.domain.pessoa.FormAtualizacaoPessoa;
import br.com.attornatus.api.domain.pessoa.FormCadastroPessoa;
import br.com.attornatus.api.domain.pessoa.Pessoa;
import br.com.attornatus.api.domain.pessoa.PessoaRepository;
import br.com.attornatus.api.infra.exceptions.IdPessoaInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repository;

    public Pessoa cadastrar(FormCadastroPessoa form){
        var pessoa = new Pessoa(form);
        return repository.save(pessoa);
    }

    public Pessoa editar(FormAtualizacaoPessoa form){
        var pessoa = buscar(form.id());
        return pessoa.atualiza(form);
    }

    public Pessoa buscar(Long id){
        return repository
                .findById(id)
                .orElseThrow(IdPessoaInvalidoException::new);
    }

    public Page<Pessoa> listar(Pageable page){
        return repository.findAll(page);
    }
}