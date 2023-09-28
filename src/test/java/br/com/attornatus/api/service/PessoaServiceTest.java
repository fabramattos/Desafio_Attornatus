package br.com.attornatus.api.service;

import br.com.attornatus.api.domain.pessoa.FormAtualizacaoPessoa;
import br.com.attornatus.api.domain.pessoa.FormCadastroPessoa;
import br.com.attornatus.api.domain.pessoa.Pessoa;
import br.com.attornatus.api.infra.exceptions.IdPessoaInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PessoaServiceTest {

    @Autowired
    private PessoaService service;

    private Pessoa pessoaCadastrada;

    @BeforeEach
    void setup(){
        pessoaCadastrada = service.cadastrar(new FormCadastroPessoa("Melon Husk", LocalDate.now()));
    }

    @Test
    @DisplayName("Dado um DTO válido, Quando tentar cadastrar, Deve cadastrar uma pessoa")
    void cadastrar1() {
        var dtoNovoCadastro = new FormCadastroPessoa("Reanu Keaves", LocalDate.now());
        var novoDadastro = service.cadastrar(dtoNovoCadastro);

        assertNotNull(novoDadastro);
        assertEquals(dtoNovoCadastro.nome(), novoDadastro.getNome());
        assertEquals(dtoNovoCadastro.dataNascimento(), novoDadastro.getDataNascimento());
        assertTrue(novoDadastro.getEnderecos().isEmpty());
    }

    @Test
    @DisplayName("Dado um DTO inválido, Quando tentar cadastrar, Deve lançar exception")
    void cadastrar2() {
        //TODO criar tratamento de erro para validations do DTO
    }

    @Test
    @DisplayName("Dado um ID e DTO válido, Quando tentar atualizar Pessoa, Deve atualizar dados da pessoa")
    void editar1() {
        var pessoaEditada = service.editar(pessoaCadastrada.getId(), new FormAtualizacaoPessoa("Nome Alterado", null));

        assertEquals("Nome Alterado", pessoaEditada.getNome());
        assertEquals(pessoaCadastrada.getDataNascimento(), pessoaEditada.getDataNascimento());
    }

    @Test
    @DisplayName("Dado um id inválido, Quando tentar editar Pessoa,Deve lançar exception")
    void editar2() {
        assertThrows(
                IdPessoaInvalidoException.class,
                () -> service.editar(-1L, new FormAtualizacaoPessoa("Nome Alterado", null))
        );
    }

    @Test
    @DisplayName("Dado um id válido, Quando tentar consultar, Deve retornar Pessoa")
    void consultar1() {
        var consulta = service.buscar(pessoaCadastrada.getId());

        assertNotNull(consulta);
        assertEquals(pessoaCadastrada, consulta);
    }

    @Test
    @DisplayName("Dado um id inválido, Quando tentar consultar, Deve lançar exception")
    void consultar2() {
        assertThrows(IdPessoaInvalidoException.class, () -> service.buscar(-1L));
    }

    @Test
    @DisplayName("Quando solicitar pessoas, Deve retornar lista")
    void listar() {
        assertNotNull(service.listar(Pageable.ofSize(10)));
    }
}