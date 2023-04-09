## ✨ Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://start.spring.io/)
- [PostgresQL](https://www.postgresql.org/)
- [Flyway](https://flywaydb.org/)

![image](image.png)



## 🚀 Como executar

- Tenha instalado o [Docker](https://www.docker.com/) para rodar o banco de dados da aplicação, ou pode iniciar manualmente tendo o PostgreSQL instalado.
- Tenha o [Maven](https://dicasdejava.com.br/como-instalar-o-maven-no-windows/) instalado
- Clone o repositório
- Rode `docker compose up -d` para iniciar o banco de dados no Posgres
- Rode `mvn spring-boot:run` para iniciar a aplicação

## Testes

```bash
# unit tests
$ mvn test
```