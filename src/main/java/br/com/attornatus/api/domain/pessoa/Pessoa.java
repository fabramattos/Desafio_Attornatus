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
        nome = form.nome();
        dataNascimento = form.dataNascimento();
    }

    public Pessoa atualiza(FormAtualizacaoPessoa form){
        if(form.nome() != null)
            this.nome = form.nome();
        if(form.dataNascimento() != null)
            this.dataNascimento = form.dataNascimento();
        return this;
    }
}

