package br.com.attornatus.api.domain.pessoa;

import br.com.attornatus.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "pessoas")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();


    public Pessoa(FormCadastroPessoa form) {
        var endereco = new Endereco(form.endereco(), this);
        endereco.setPrincipal(true); // Cadastro novo obriga criação de endereço, o marca como favorito por padrão.

        nome = form.nome();
        dataNascimento = form.dataNascimento();
        enderecos.add(endereco);
    }

    public void atualiza(FormAtualizacaoPessoa form){
        if(form.nome() != null)
            nome = form.nome();
        if(form.dataNascimento() != null)
            dataNascimento = form.dataNascimento();
    }

}

