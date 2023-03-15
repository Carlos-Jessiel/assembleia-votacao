# 💻 Sobre o projeto

## Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.

Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

- Cadastrar uma nova pauta;

- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);

- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);

- Contabilizar os votos e dar o resultado da votação na pauta.

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

---

## ⚙️ Funcionalidades

- [x] ASSEMBLEIA: Cadastro, listagem, busca, delete, abertura de pauta e resultado de votação;
- [x] ASSOCIADO: Cadastro, listagem, busca e delete;
- [x] VOTO: Cadastro de voto;

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[PostgreSQL](https://www.postgresql.org/)**
- **[Lombok](https://projectlombok.org)**

---

## 📄 Documentação

API documentada em Swagger para facilitar o entendimento das funcionalidade da aplicação.

---

## 📝 O que queremos mostrar com essa API:

  * Simplicidade no design da solução
  * Organização do código
  * Arquitetura do projeto
  * Boas práticas de programação (manutenibilidade, legibilidade etc)
  * Possíveis bugs
  * Tratamento de erros e exceções
  * Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução
  * Limpeza do código
  * Documentação do código e da API
  * Mensagens e organização dos commits

  