# LibraryFlow API — Gerenciamento de Biblioteca

API REST educacional para **livros, leitores, empréstimos, devoluções e disponibilidade de exemplares**.

## Tecnologias
Java 21+, Spring Boot 3.5.7, Spring Web, Spring Data JPA, PostgreSQL 16, Flyway, Jakarta Bean Validation, JUnit 5, Docker Compose e GitHub Actions.

## Funcionalidades
- Cadastrar e consultar livros (ISBN único).
- Cadastrar e consultar leitores (e-mail único).
- Empréstimos com prazo de 1 a 60 dias.
- Controle de exemplares: empréstimo diminui a quantidade disponível; devolução aumenta.
- Impedir empréstimos sem estoque e devolução duplicada.
- Listagem de empréstimos ativos, histórico do leitor e indicação de atraso.
- Transações JPA e bloqueio otimista `@Version` no livro para evitar perda de atualização concorrente.

## Executar
Requisitos: JDK 21+, Maven e Docker.
```bash
docker compose up -d
mvn clean test
mvn spring-boot:run
```
No Eclipse: **File > Import > Maven > Existing Maven Projects**.

API: `http://localhost:8080` • PostgreSQL: `localhost:5438`. As credenciais do `docker-compose.yml` são exclusivamente para desenvolvimento. Variáveis `DB_URL`, `DB_USER` e `DB_PASSWORD` podem sobrescrevê-las.

## Endpoints
| Método | Rota | Operação |
|---|---|---|
| POST | `/api/v1/books` | Cadastrar livro |
| GET | `/api/v1/books` | Listar livros |
| GET | `/api/v1/books/{id}` | Consultar livro |
| POST | `/api/v1/members` | Cadastrar leitor |
| GET | `/api/v1/members` | Listar leitores |
| POST | `/api/v1/loans` | Registrar empréstimo |
| GET | `/api/v1/loans/{id}` | Consultar empréstimo |
| GET | `/api/v1/members/{id}/loans` | Histórico de empréstimos |
| GET | `/api/v1/loans/active` | Listar empréstimos ativos |
| PATCH | `/api/v1/loans/{id}/return` | Devolver livro |

### Exemplo: cadastrar livro
```json
{"isbn":"9780134685991","title":"Effective Java","author":"Joshua Bloch","copies":3}
```

### Exemplo: cadastrar leitor
```json
{"name":"Maria Souza","email":"maria@example.com"}
```

### Exemplo: emprestar
```json
{"bookId":1,"memberId":1,"days":14}
```

## Regras importantes
Empréstimos e devoluções usam `@Transactional`: operações em banco confirmam ou revertem juntas. `@Version` previne perda silenciosa de atualização da quantidade de exemplares; uma atualização simultânea pode falhar com HTTP 409 e o cliente pode tentar novamente. A regra de atraso compara `dueAt` com o instante atual, e empréstimos devolvidos deixam de ser considerados atrasados.

## Estrutura
`domain/` entidades e regras • `repository/` JPA • `service/` casos de uso e transações • `api/` controllers, DTOs e exceções • `db/migration/` SQL versionado.

## Limitações
**Sem autenticação nem autorização:** não publique com dados pessoais reais. Não implementa reservas, multas, notificações, renovação, buscas paginadas nem identificação individual de cada exemplar. O pipeline CI executa testes unitários; não substitui testes de integração com PostgreSQL, especialmente os de concorrência.
