package br.com.attornatus.api.infra.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Teste técnico BackEnd Attornatus")
                        .description("""
                                Api simples para gerenciar Pessoas. Ela deve permitir:

                                - Criar uma pessoa
                                - Editar uma pessoa
                                - Consultar uma pessoa
                                - Listar pessoas
                                - Criar endereço para pessoa
                                - Listar endereços da pessoa
                                - Poder informar qual endereço é o principal da pessoa

                                Para acessar diretamente o banco em memoria H2:

                                - *http://localhost:8080/h2-console*
                                - **JDBC URL: jdbc:h2:mem:dev-db**
                                - **User Name: sa**
                                - **Password: root**
                                """
                        )
                        .contact(new Contact()
                                .name("Felipe Mattos")
                                .email("fabramattos@gmail.com"))
                );
    }
}
