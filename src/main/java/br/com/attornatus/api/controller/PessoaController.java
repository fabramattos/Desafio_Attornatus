package br.com.attornatus.api.controller;

import br.com.attornatus.api.domain.pessoa.*;
import br.com.attornatus.api.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService service;

    @PostMapping
    public ResponseEntity<ViewPessoaDetalhado> cadastrar(@RequestBody @Valid FormCadastroPessoa form, UriComponentsBuilder uriBuilder) {
        var pessoa = service.cadastrar(form);
        var uri = uriBuilder.path("pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new ViewPessoaDetalhado(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViewPessoaDetalhado> atualizar(@RequestBody @Valid FormAtualizacaoPessoa form, UriComponentsBuilder uriBuilder) {
        var pessoa = service.editar(form);
        var uri = uriBuilder.path("pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new ViewPessoaDetalhado(pessoa));
    }

    @GetMapping
    public ResponseEntity<Page<ViewPessoaSimplificado>> listar(Pageable pageable) {
        return ResponseEntity.ok(service
                .listar(pageable)
                .map(ViewPessoaSimplificado::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewPessoaDetalhado> detalhar(@PathVariable Long id) {
        Pessoa pessoa = service.buscar(id);
        return ResponseEntity.ok(new ViewPessoaDetalhado(pessoa));

    }
}