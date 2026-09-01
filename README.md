# Criar um dto para as entidades

### Problemas atuais do projeto
As respostas retornam a senha e o CPF do usuário.
Como o cadastro recebe a entidade inteira, nada impede o cliente de mandar um `createdAt` falso ou de já criar a conta como `active: false`

### Requisitos
- Após a mudança, o endpoint `POST /users` continua recebendo senha e CPF normalmente.
- Nenhuma resposta da API expõe `password` ou `cpf`.
- O `POST /users` não pode aceitar `createdAt` nem `active` vindos do cliente.

## Critérios de avaliação

- Existe um DTO de entrada e um DTO de saída, com responsabilidades separadas.
- O DTO de entrada não declara `createdAt` nem `active`, de modo que o cliente
  não consegue definir esses campos.
- A conversão entre entidade e DTO está isolada na camada de serviço.
- Nenhuma resposta da API expõe `password` ou `cpf`.
- O controller não referencia mais a entidade `User`.
- O código continua compilando e a API continua funcionando.

## A documentação da API (Swagger UI) fica disponível em:

```
http://localhost:8080/swagger-ui.html
```
