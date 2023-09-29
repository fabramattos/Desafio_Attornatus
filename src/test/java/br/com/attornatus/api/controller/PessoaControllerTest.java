package br.com.attornatus.api.controller;

import br.com.attornatus.api.domain.pessoa.FormAtualizacaoPessoa;
import br.com.attornatus.api.domain.pessoa.FormCadastroPessoa;
import br.com.attornatus.api.domain.pessoa.Pessoa;
import br.com.attornatus.api.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@Transactional
@ActiveProfiles("test")
class PessoaControllerTest {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<FormCadastroPessoa> jacksonCadastroPessoa;
    @Autowired
    private JacksonTester<FormAtualizacaoPessoa> jacksonAtualizacaoPessoa;

    private static final String URI = "/pessoas";
    private Pessoa pessoaCadastrada;

    @BeforeEach
    void setup() {
        pessoaCadastrada = pessoaService.cadastrar(new FormCadastroPessoa("Pessoa Teste", LocalDate.now()));
    }

    @Test
    @DisplayName("Dado um Dto válido, Quando tentar POST para cadastrar usuário, Deve retornar HTTP 201 Created")
    void cadastrar1() throws Exception {
        mvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonCadastroPessoa.write(dtoCadastroPessoa()).getJson())
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Dado um Dto inválido, Quando tentar POST para cadastrar usuário, Deve retornar HTTP BadRequest")
    void cadastrar2() throws Exception {
        mvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Dado um idPessoa e Dto válido, Quando tentar PUT para atualizar pessoa, Deve retornar HTTP 200 Ok")
    void atualizar1() throws Exception {
        var idPessoa = pessoaCadastrada.getId();
        mvc.perform(put(URI + "/" + idPessoa)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonAtualizacaoPessoa.write(dtoAtualizacaoPessoa()).getJson()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Dado um idPessoa válido mas com DTO inválido, Quando tentar PUT para atualizar pessoa, Deve retornar HTTP 400 BadRequest")
    void atualizar2() throws Exception {
        var idPessoa = pessoaCadastrada.getId();
        mvc.perform(put(URI + "/" + idPessoa))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Dado um idPessoa inválido, Quando tentar PUT para atualizar pessoa, Deve retornar HTTP 400 BadRequest")
    void atualizar3() throws Exception {
        var idPessoa = -1L;
        mvc.perform(put(URI + "/" + idPessoa)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonAtualizacaoPessoa.write(dtoAtualizacaoPessoa()).getJson()))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Quando tentar GET para listar pessoa, Deve retornar HTTP 200 Ok")
    void listar() throws Exception {
        mvc.perform(get(URI)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Dado um idPessoa válido, Quando tentar GET para detalhar pessoa, Deve retornar HTTP 200 Ok")
    void detalhar1() throws Exception {
        var idPessoa = pessoaCadastrada.getId();
        mvc.perform(get(URI + "/" + idPessoa))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Dado um idPessoa inválido, Quando tentar GET para detalhar pessoa, Deve retornar HTTP BadRequest")
    void detalhar2() throws Exception {
        var idPessoa = -1L;
        mvc.perform(get(URI + "/" + idPessoa))
                .andExpect(status().isBadRequest());
    }


    private FormCadastroPessoa dtoCadastroPessoa() {
        return new FormCadastroPessoa(
                "Fulano Beltrano",
                LocalDate.now());
    }

    private FormAtualizacaoPessoa dtoAtualizacaoPessoa() {
        return new FormAtualizacaoPessoa(
                "Fulano Beltrano",
                null);
    }
}