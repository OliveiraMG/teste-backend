Este projeto é um back-end desenvolvido com Spring Boot, configurado para funcionar com Docker e Docker Compose.

## Requisitos

- Docker
- Docker Compose
- JDK 17

## Configuração e Inicialização

### 1. Clone o Repositório

Clone o repositório do projeto e navegue até o diretório:

```bash
git clone https://github.com/OliveiraMG/teste-backend.git
cd teste-backend
```

### 2. Configuração do Docker

Certifique-se de que o Docker e o Docker Compose estão instalados e funcionando. Você pode verificar com:

```bash
docker --version
docker-compose --version
```

### 3. Configuração do Banco de Dados e Serviço Spring Boot

Para iniciar o banco de dados MySQL e o serviço Spring Boot usando Docker Compose, execute:

```bash
docker-compose up --build
```
Isso irá criar as imagens necessárias e iniciar os contêineres do banco, definidos no docker-compose.yml

### 4. Verificação dos Containers
Para verificar se os contêineres estão rodando, execute:

```bash
docker ps
```
Você deve ver containers em execução para o MySQL e o Spring Boot.

### 5. Acesso ao Serviço
O serviço Spring Boot estará acessível em http://localhost:8080.
Caso esteja rodando irá aparecer a página "Whitelabel Error Page"

### 6. Inicializar Front-End
Para inicializar o front-end, navegue até o repositório dele e siga as demais instrunções.


### Estrutura do Projeto
- Dockerfile: Define a imagem Docker para o serviço Spring Boot.
- docker-compose.yml: Configura os serviços necessários (MySQL e Spring Boot) para o projeto.
- data.sql: Script SQL para inicializar o banco de dados com dados iniciais.