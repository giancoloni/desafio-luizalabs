# Desafio Luizalabs

Este projeto é uma solução para o desafio proposto pela Luizalabs. Ele tem como objetivo criar uma aplicação Java que gerencia uma lista de desejos (chamei de Wishlist) de produtos para os usuários.

## Sumário

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [Testando a Aplicação](#testando-a-aplicação)

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes itens instalados em seu sistema:

- [Java JDK 22+](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)

## Instalação

Siga os passos abaixo para instalar e configurar o projeto:

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/giancoloni/desafio-luizalabs.git
    cd desafio-luizalabs
    ```

2. **Compile o projeto usando Maven:**

    ```bash
    mvn clean install
    ```

## Configuração

Antes de executar a aplicação, é necessário configurar o MongoDB. Utilize Docker para rodar um contêiner do MongoDB:

1. **Inicie um contêiner MongoDB:**

    ```bash
    docker run --name mongodb -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin mongo
    ```

2. **Configure a aplicação para se conectar ao MongoDB** ajustando o arquivo `application.properties` na pasta `src/main/resources`:

    ```properties
    spring.data.mongodb.uri=mongodb://admin:admin@localhost:27017/desafio-luizalabs?authSource=admin
    spring.jpa.hibernate.ddl-auto=update
    server.port=8080
    ```

## Uso

Para iniciar a aplicação, execute o seguinte comando:

```bash
mvn spring-boot:run
```

## Testando a aplicação

Para testar a aplicação, você pode usar o Swagger no endpoint abaixo:
```
http://localhost:8080/swagger-ui/index.html
```

