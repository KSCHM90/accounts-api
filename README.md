# Accounts API

API REST simples para criação e listagem de contas bancárias do CoraBank.

## Stack

- **Java**: 17+
- **Framework**: Spring Boot 3.5.10
- **Banco de Dados**: H2 (em memória)
- **Build**: Maven

## Como rodar

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/KSCHM90/accounts-api.git
   cd accounts-api
   ```

2. **Instale as dependências e rode a aplicação**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   Ou use o Maven wrapper (Windows):
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

3. **Acesse a aplicação**:
   - API base: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`
   - Credenciais H2:
     - **JDBC URL**: `jdbc:h2:mem:testdb`
     - **User**: `sa`
     - **Password**:

## Endpoints

### 1. Criar Conta
- **Endpoint**: `POST /accounts`
- **Content-Type**: `application/json`
- **Request**:
  ```json
  {
    "name": "Nome do Usuário",
    "cpf": "12345678901"
  }
  ```
- **Response**: `201 Created`
  ```json
  {
    "id": 1,
    "name": "Nome do Usuário",
    "cpf": "12345678901"
  }
  ```

### 2. Listar Contas
- **Endpoint**: `GET /accounts`
- **Response**: `200 OK`
  ```json
  [
    {
      "id": 1,
      "name": "Nome do Usuário",
      "cpf": "12345678901"
    }
  ]
  ```

## Exemplos com cURL

### Criar conta
```bash
curl -X POST http://localhost:8080/accounts \
  -H "Content-Type: application/json" \
  -d '{"name": "João Silva", "cpf": "12345678901"}'
```

### Listar contas
```bash
curl http://localhost:8080/accounts
```

## CORS

A API está configurada para aceitar requisições de qualquer origem (`@CrossOrigin`), facilitando a integração com o frontend React.

## Notas

- O banco de dados é em memória (H2), então todos os dados são perdidos quando a aplicação é encerrada
- A porta padrão é **8080**
- Não há validações ou tratamento de erros elaborados (conforme escopo do desafio)

