# DSList Backend

Uma API REST para gerenciar listas de jogos, construída com Spring Boot.
Este projeto foi desenvolvido no Intensivão Java Spring da devsuperior.

---

### Tecnologias

- **Backend:** Java 17+, Spring Boot, JPA, Maven
- **Banco de Dados:** PostgreSQL (produção), H2 (testes)
- **Deploy:** Railway
- **Contêineres:** Docker, Docker Compose

---

### Como Rodar Localmente

**Pré-requisitos:** Java 17+, Docker, Maven.

1.  **Clone o projeto:**
    `git clone [link do seu repositório]`
2.  **Suba o banco de dados com Docker Compose:**
    `docker-compose up -d`
3.  **Execute a aplicação:**
    `./mvnw spring-boot:run`

---

### O que o projeto faz

Este projeto demonstra a criação de uma API REST completa para gerenciar um catálogo de jogos, com funcionalidades como:

-   Listar coleções de jogos.
-   Buscar detalhes de jogos por ID.
-   Mover jogos entre a lista, alterando a posição.

---

### Diagrama de Domínio

O modelo de dados do projeto:

![Modelo de domínio DSList](https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/dslist-model.png)
