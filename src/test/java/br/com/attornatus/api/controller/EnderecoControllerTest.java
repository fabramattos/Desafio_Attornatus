package br.com.attornatus.api.controller;

import br.com.attornatus.api.domain.endereco.FormCadastroEndereco;
import br.com.attornatus.api.domain.pessoa.FormCadastroPessoa;
import br.com.attornatus.api.domain.pessoa.Pessoa;
import br.com.attornatus.api.infra.exceptions.IdPessoaInvalidoException;
import br.com.attornatus.api.service.EnderecoService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@Transactional
@ActiveProfiles("test")
class EnderecoControllerTest {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<FormCadastroEndereco> jacksonTester;

    private static final String URI = "/enderecos";
    private Pessoa pessoaCadastrada;

    @BeforeEach
    void setup() {
        pessoaCadastrada = pessoaService.cadastrar(new FormCadastroPessoa("Pessoa Teste", LocalDate.now()));
        enderecoService.cadastrar(pessoaCadastrada.getId(), dtoCadastroEndereco(0));
    }

    @Test
    @DisplayName("Dado um Dto válido, Quando solicitado um POST em um idPessoa válido, Deve retornar HTTP Created")
    void cadastrar1() throws Exception {
        var idPessoa = pessoaCadastrada.getId();
        mvc.perform(
                        post(URI + "/" + idPessoa)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jacksonTester.write(dtoCadastroEndereco(1)).getJson())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.principal").value(false));
    }

    @Test
    @DisplayName("Dado um idPessoa inválido, Quando solicitado um POST, Deve retornar HTTP BadRequest")
    void cadastrar2() throws Exception {
        var idInvalido = -1;
        mvc.perform(
                        post(URI + "/" + idInvalido)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jacksonTester.write(dtoCadastroEndereco(1)).getJson())
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensagem").value(IdPessoaInvalidoException.getMensagem()));
    }

    @Test
    @DisplayName("Dado um idPessoa válido, Quando solicitado um GET, Deve retornar HTTP Ok")
    void listar1() throws Exception {
        var idPessoa = pessoaCadastrada.getId();
        mvc.perform(get(URI + "/" + idPessoa))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Dado um idPessoa inválido, Quando solicitado um GET, Deve retornar HTTP BadRequest")
    void listar() throws Exception {
        mvc.perform(get(URI + "/" + -1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensagem").value(IdPessoaInvalidoException.getMensagem()));
    }

    @Test
    @DisplayName("Dado id's validos, Quando solicitado um PUT, Deve retornar HTTP Ok")
    void favoritar() throws Exception {
        var idPessoa = pessoaCadastrada.getId();
        var idEndereco = pessoaCadastrada.getEnderecos().get(0).getId();

        mvc.perform(put(URI + "/" + idPessoa + "/" + idEndereco))
                .andExpect(status().isOk());
    }


    private FormCadastroEndereco dtoCadastroEndereco(Integer numero) {
        return new FormCadastroEndereco(
                "12345000",
                "Cidade",
                "Logradouro",
                numero,
                false);
    }
}