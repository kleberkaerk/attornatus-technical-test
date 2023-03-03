# Teste técnico Attornatus

# Perguntas do teste

1. Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?
        
        Os requisitos explícitos e os requisitos implícitos.
2. Em qual etapa da implementação você considera a qualidade de software?

        Na etapa de testes.

# Descrição

Uma API para gerenciar pessoas e seus endereços.

# Tecnologias utilizadas

1. Java 17
2. Maven
3. Spring Boot
4. Banco de dados H2

# Funcionalidades

* Buscar todas as pessoas.
* Buscar determinada pessoa.
* Cadastrar uma nova pessoa.
* Atualizar uma pessoa existente.
* Cadastrar o endereço de uma pessoa.
* Buscar todos os endereços de uma pessoa.
* Atualizar o endereço principal de uma pessoa.

# Execução 

Para executar a aplicação localmente siga os seguintes passos:

* Navegue até o diretório raiz do projeto (onde se encontra o arquivo pom).
* Abra um terminal e execute o comando `mvn clean package spring-boot:run`.

A partir deste ponto, todas as rotas da aplicação estarão disponíveis para serem utilizadas. 

## Rotas

1. `http://localhost:8080/persons/find-all` -> Busca todos as pessoas.
2. `http://localhost:8080/persons/find-by-id/{personId}` -> Busca determinada pessoa.
3. `http://localhost:8080/persons/register` -> Cadastra uma nova pessoa.
4. `http://localhost:8080/persons/update` -> Atualiza uma pessoa.
5. `http://localhost:8080/addresses/find-by-person/{personId}` -> Busca todos os endereços de uma pessoa.
6. `http://localhost:8080/addresses/register` -> Cadastra um novo endereço para uma pessoa.
7. `http://localhost:8080/addresses/{personId}/{addressId}` -> Atualiza o endereço principal de uma pessoa.

# Testes unitários

Para executar os testes unitários siga os seguintes passos:

* Navegue até o diretório raiz do projeto (onde se encontra o arquivo pom).
* Abra um terminal e execute o comando `mvn test`.

# Acesso ao console do H2

Para acessar o console do banco H2 siga os seguintes passos:
* acesse a `http://localhost:8080/h2-console`.
* No campo <strong>JDBC URL</strong> insira o valor `jdbc:h2:mem:TECHNICAL_TEST_DATABASE?createDatabaseIfNotExist=true`.
* No campo <strong>password</strong> insira o valor `admin`.

# Documentação swagger

Para ler a documentação swagger da aplicação localmente acesse `http://localhost:8080/swagger-ui/index.html`.

Para ler a documentação swagger da aplicação que está no ar acesse `http://ec2-3-87-88-139.compute-1.amazonaws.com:8080/swagger-ui/index.html`.

# Autor

[<img src="./imagem.jpg" width=115><br><sub>Kleber Kaerk</sub>](https://github.com/kleberkaerk)