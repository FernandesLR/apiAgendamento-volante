# 🐾 Agendamento Volante - Backend

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)
[![JWT](https://img.shields.io/badge/JWT-JSON_Web_Token-black?style=for-the-badge&logo=jsonwebtokens)](https://jwt.io/)

## 🚀 Sobre o Projeto

O projeto resolve o desafio logístico do atendimento volante. O cliente necessitava de uma plataforma que substituísse o agendamento informal e descentralizado, oferecendo uma solução robusta para:

Centralização de Pedidos: Clínicas solicitam exames via plataforma, eliminando erros de comunicação.

Mobilidade: Arquitetura via JWT pronta para integração com aplicativos iOS e Android, essencial para o veterinário que trabalha em trânsito.

Segurança de Dados: Controle rigoroso de acesso para garantir que dados sensíveis de pacientes e faturamento sejam acessados apenas por usuários autorizados.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.x
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL
- **Segurança:** Spring Security + JWT (JSON Web Token)
- **Produtividade:** Lombok & Spring DevTools
- **Arquitetura:** REST API com padrão DTO e Service Layer

## 🔐 Segurança e Autenticação

O sistema utiliza **RBAC (Role-Based Access Control)** para gerenciar permissões. 
- **Entidade Clínica:** Implementa `UserDetails` para integração nativa com o ecossistema Spring Security.
- **Tokens JWT:** Autenticação *Stateless*, garantindo que o backend esteja preparado para consumo por aplicações mobile e SPAs.
- **Criptografia:** Todas as senhas são protegidas utilizando o algoritmo **BCrypt**.

## 🏗️ Estrutura do Banco de Dados (Resumo)

- **ClinicaEntity:** Dados da clínica (CNPJ, Nome, Email, Senha).
- **RolesEntity:** Tabela de permissões (Ex: `ROLE_CLINICA`, `ROLE_ADMIN`).
- **AgendamentoEntity:** Relacionamento `ManyToOne` com as clínicas.

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- JDK 21
- Maven 3.x
- PostgreSQL ativo

### Passos
1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/agendamento-volante.git](https://github.com/seu-usuario/agendamento-volante.git)
