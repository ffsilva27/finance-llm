# Finance LLM

> Assistente financeiro inteligente desenvolvido com Spring Boot, LangChain4J e OpenRouter, capaz de interagir em linguagem natural para consultar saldos, registrar transações e responder perguntas financeiras utilizando ferramentas (Tools) integradas ao domínio da aplicação.

## ✨ Visão Geral

O **Finance LLM** é um projeto de estudos focado na construção de uma aplicação de IA Generativa utilizando Java e o ecossistema Spring.

A aplicação demonstra como integrar Large Language Models (LLMs) com regras de negócio reais através do conceito de **AI Tools (Function Calling)**, permitindo que o modelo execute operações como:

- Consultar saldo da conta
- Listar transações
- Registrar receitas e despesas
- Identificar automaticamente o usuário da conversa
- Interagir via API REST ou Telegram

O projeto utiliza **LangChain4J** como camada de integração com o modelo de linguagem e **OpenRouter** como provedor da LLM.

---

## 🏗 Arquitetura

O projeto foi organizado seguindo uma separação clara de responsabilidades.

```
src/main/java/com/study/llm
├── configuration     # Configuração da IA, cache e OpenRouter
├── controller        # API REST
├── domain            # Regras de negócio e AI Tools
├── infrastructure    # Persistência, clientes externos e schedulers
```

### Fluxo da aplicação

```text
Usuário
      │
      ▼
REST API / Telegram
      │
      ▼
FinanceAssistant (LangChain4J)
      │
      ▼
LLM (OpenRouter)
      │
      ▼
Tool Calling
      │
      ▼
Domain Services
      │
      ▼
Banco de Dados
```

---

## 🚀 Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- LangChain4J
- OpenRouter
- Caffeine Cache
- Docker Compose
- Telegram Bot API
- Maven

---

## 📂 Estrutura do Projeto

### configuration

Responsável pela configuração da aplicação e da integração com a LLM.

- OpenRouter
- Assistant
- Cache
- Configuração do modelo

### controller

Disponibiliza os endpoints REST para interação com o assistente.

### domain

Contém toda a lógica de negócio da aplicação.

Aqui ficam:

- Services
- DTOs
- Mappers
- AI Tools

As Tools representam as funções que podem ser chamadas pela LLM.

Exemplo:

- BalanceTools
- TransactionTools
- UserIdentifierTool

### infrastructure

Responsável pelos detalhes de implementação.

- Repositórios JPA
- Entidades
- Cliente do Telegram
- Scheduler de polling

---

## ⚙️ Como executar

### Clonar

```bash
git clone https://github.com/ffsilva27/finance-llm.git
```

### Banco

```bash
docker compose up -d
```

### Executar

```bash
./mvnw spring-boot:run
```

---

## 🔧 Configuração

Configure as propriedades da aplicação no `application.yml`.

Exemplo:

```yaml
openrouter:
  api-key: ${OPENROUTER_API_KEY}

telegram:
  token: ${TELEGRAM_TOKEN}
```

---

## 🤖 AI Tools

A aplicação expõe ferramentas para que a LLM execute ações reais.

Atualmente estão disponíveis:

| Tool | Descrição |
|------|-----------|
| BalanceTools | Consulta saldo |
| TransactionTools | Lista e registra transações |
| UserIdentifierTool | Identifica o usuário da conversa |

---

## 💬 Interfaces disponíveis

- REST API
- Telegram Bot

---

## 📌 Roadmap

- [x] Integração com OpenRouter
- [x] Tool Calling
- [x] Persistência com PostgreSQL
- [x] Integração com Telegram
- [ ] Memória persistente
- [ ] RAG para documentação financeira
- [ ] Tool de consulta financeira baseada em RAG para responder dúvidas utilizando uma base de conhecimento especializada
- [ ] MCP Server
- [ ] Observabilidade
- [ ] Testes de integração
- [ ] Suporte a múltiplos modelos

---

## 🎯 Objetivo

Este projeto foi desenvolvido como laboratório para estudar aplicações de IA Generativa em Java, explorando conceitos como:

- Function Calling
- AI Agents
- LangChain4J
- Engenharia de Prompt
- Integração entre LLMs e regras de negócio
- Arquiteturas para aplicações de IA

---

## 📄 Licença

Projeto desenvolvido para fins de estudo.
