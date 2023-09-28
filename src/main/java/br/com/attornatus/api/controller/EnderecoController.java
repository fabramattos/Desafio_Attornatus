package br.com.attornatus.api.controller;

import br.com.attornatus.api.domain.endereco.FormCadastroEndereco;
import br.com.attornatus.api.domain.endereco.ViewEndereco;
import br.com.attornatus.api.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping("/{idPessoa}")
    @Transactional
    public ResponseEntity<ViewEndereco> cadastrar(@RequestBody @Valid FormCadastroEndereco form,
                                                  @PathVariable Long idPessoa,
                                                  UriComponentsBuilder uriBuilder) {
        var endereco = service.cadastrar(idPessoa, form);
        var uri = uriBuilder.path("endereco/"+idPessoa+"/{idEndereco}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new ViewEndereco(endereco));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ViewEndereco>> listar(@PathVariable Long id) {
        return ResponseEntity.ok(service
                .listarPorPessoa(id)
                .stream()
                .map(ViewEndereco::new)
                .toList());
    }

    @PutMapping("/{idPessoa}/{idEndereco}")
    @Transactional
    public ResponseEntity<List<ViewEndereco>> favoritar(@PathVariable Long idPessoa,
                                                        @PathVariable Long idEndereco) {
        return ResponseEntity.ok(service
                .favoritarEndereco(idPessoa, idEndereco)
                .stream()
                .map(ViewEndereco::new)
                .toList());
    }


}
