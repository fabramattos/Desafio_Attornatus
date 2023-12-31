# Desafio Attornatus
## Teste Técnico - BackEnd

API básica para controle de cadastro de Pessoa e Endereços

| :placard:  |     |
| -------------  | --- |
| :sparkles: Nome        | **Desafio Attornatus**
| :label: Tecnologias | java, spring boot, H2, Swagger
| :rocket: URL         | (sem deploy por enquanto)

## Descrição do Desafio

Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir: 
- Criar uma pessoa
- Editar uma pessoa
- Consultar uma pessoa
- Listar pessoas
- Criar endereço para pessoa
- Listar endereços da pessoa
- Poder informar qual endereço é o principal da pessoa

Uma Pessoa deve ter os seguintes campos: 
- Nome
- Data de nascimento
- Endereço:
  - Logradouro
  - CEP
  - Número
  - Cidade

## Requisitos 
- Todas as respostas da API devem ser JSON 
- Banco de dados H2
- Testes unitários
- Clean Code

## Como Executar
A api pode ser lançada direto pela IDE apenas executando a Main.
Apos em execução, ficará disponivel na porta 8080, podendo ser testata com Postman:
 - http://localhost:8080/(endpoints)
Para testar utilizando o Swagger:
 - [Link Swagger Ui](http://localhost:8080/swagger-ui/)

## TODO
Passos que considero interessantes para incrementar a api se houvesse tempo:
 - implementar verificações nos testes para checar o json recebido nas Response da API
 - security
 - tratar erros lançados pelo Bean Validate  e retornar em json para o usuario
 - pipeline CI/CD para rodar testes em branches antes de realizar merge na master
 - deploy em algum servidor (Railway.app?)
