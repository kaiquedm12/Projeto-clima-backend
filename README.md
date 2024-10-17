# Projeto Intermediário - API de Clima

## Descrição

Este projeto é um web service que consulta a API do OpenWeather para retornar dados do clima de uma cidade especificada. Além disso, possui uma rota para processar dados recebidos via POST e retornar um status.

## Requisitos

- Java 17
- Maven
- Docker
- Chave de API OpenWeather (substitua no código)

## Como rodar o projeto

### Usando Maven

1. Clone o repositório.
2. Compile o projeto:
   ```bash
   mvn clean package
   ```
3. Rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```

### Usando Docker

1. Clone o repositório.
2. Construa a imagem Docker:
   ```bash
   docker-compose build
   ```
3. Inicie o container:
   ```bash
   docker-compose up
   ```

A aplicação será executada na porta 8080.

## Rotas

### Rota GET `/api/clima`

Consulta o clima de uma cidade.

**Exemplo de requisição:**

```bash
GET http://localhost:8080/api/clima?cidade=Criciúma
```

**Exemplo de resposta:**

```json
{
  "weather": [{ "description": "nublado" }],
  "main": { "temp": 15.5 }
}
```

### Rota POST `/api/processo`

Processa os dados recebidos.

**Exemplo de requisição:**

```bash
POST http://localhost:8080/api/processo
Content-Type: application/json
{
  "name": "Exemplo",
  "description": "Alguma informação"
}
```

**Exemplo de resposta:**

```json
{
  "name": "Exemplo",
  "description": "Alguma informação",
  "status": "Dados recebidos e processados com sucesso!"
}
```

### Rota GET `/sobre`

Retorna informações sobre o projeto.

**Exemplo de resposta:**

```json
{
  "estudante": "seu nome",
  "projeto": "nome do projeto"
}
```

### Chave API OpenWeather

Substitua `"SUA_CHAVE_API_OPENWEATHER"` pela sua chave da API OpenWeather no arquivo `Climainfos.java`.

---

## Executando o Projeto com Docker

1. **Certifique-se de ter o Docker instalado**:
   - Se você não tem o Docker instalado, pode baixá-lo a partir do site oficial [Docker](https://www.docker.com/).

2. **Construa a imagem Docker**:
   - No terminal, navegue até o diretório onde você salvou o arquivo `docker-compose.yml` e execute o seguinte comando:
   ```bash
   docker-compose build
   ```

3. **Execute o container Docker**:
   - Ainda no mesmo diretório, rode o seguinte comando para iniciar o container:
   ```bash
   docker-compose up
   ```

4. **Acesse a aplicação**:
   - Acesse a aplicação no navegador ou através de uma ferramenta como Postman em: 
     - `http://localhost:8080`.

---

## Explicação dos Arquivos

- **Dockerfile**: Este arquivo define como a aplicação será construída e configurada dentro de um container Docker.
- **docker-compose.yml**: Este arquivo facilita a execução do projeto, permitindo que você construa e execute o container com um único comando.
- **README.md**: Fornece um guia completo sobre como executar o projeto e exemplos de requisições e respostas.

---

Com essas instruções e arquivos, o projeto estará configurado para rodar tanto localmente quanto em um ambiente Docker, cumprindo os requisitos especificados.
