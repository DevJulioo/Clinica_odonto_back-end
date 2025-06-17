# 🦷 ClinicaOdonto - Backend

Um sistema de gerenciamento robusto para clínicas odontológicas, construído com Spring Boot, focado na segurança e na eficiência de dados.

## ✨ Visão Geral

Este projeto é o coração da sua clínica odontológica digital. Ele gerencia todas as informações cruciais, desde dados de pacientes e médicos até agendamentos de consultas e especialidades. Com uma API RESTful segura, ele serve como a espinha dorsal para qualquer aplicação frontend (web, mobile, desktop).

## 🚀 Tecnologias Utilizadas

* **Spring Boot 3.5.0**: Framework para o desenvolvimento rápido de aplicações Java.
* **Spring Data JPA / Hibernate**: Para persistência e interação com o banco de dados.
* **Spring Security**: Gerenciamento de autenticação (JWT) e autorização baseada em funções (RBAC).
* **JWT (JSON Web Tokens)**: Para autenticação segura e sem estado.
* **MySQL Connector/J**: Driver para conexão com o banco de dados MySQL.
* **OpenPDF**: Para geração de relatórios em PDF.
* **Maven**: Gerenciamento de dependências e construção do projeto.
* **Java 21**: Linguagem de programação.

## 🛠️ Configuração e Execução

### Pré-requisitos

Certifique-se de ter instalado em sua máquina:

* **Java Development Kit (JDK) 21**
* **Maven**
* **MySQL Server** (versão 8.0.42 ou superior, conforme seu log)

### Configuração do Banco de Dados

1.  Crie um banco de dados MySQL chamado `clinics_dev` (ou o nome que preferir).
   
2.  Ajuste as configurações de conexão em `src/main/resources/application-dv_work.properties` (ou `application-dv_home.properties` se aplicável):
    ```properties
    spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/clinics_dev}
    spring.datasource.username=${DB_USERNAME:root}
    spring.datasource.password=${DB_PASSWORD:root} # Ou sua senha de banco de dados
    ```
    Você pode alterar `root` para o seu usuário e senha do MySQL.

### Geração da Senha do Administrador

Execute a classe `biovitta.com.clinics.PasswordGenerator` para gerar uma senha criptografada que você pode usar para um usuário administrador.
Exemplo (via IDE ou compilando e executando a classe):
```bash
# Navegue até a pasta raiz do seu projeto backend
mvn compile exec:java -Dexec.mainClass="biovitta.com.clinics.PasswordGenerator"
````
## 🔒 Segurança e Autorização (RBAC)

O sistema implementa autenticação via JWT e autorização baseada em funções (RBAC). As funções (Permissao.java) são: ADMIN, MEDICO e PACIENTE. Cada endpoint tem permissões específicas, conforme definido em AutorizacaoConfig.java.

 - Login/Registro Público: POST /biovitta/auth/login e POST /biovitta/auth/register (para pacientes) são abertos.
 - Acesso por Função:
 - ADMIN: Possui acesso total e pode gerenciar usuários, médicos, pacientes, especialidades, consultas e relatórios.
 - MEDICO: Pode gerenciar suas próprias informações, ver todos os médicos, pacientes e consultas, além de agendar/editar algumas consultas.
 - PACIENTE: Pode registrar-se, acessar seu perfil, ver seus próprios dados de consulta, e agendar novas consultas.

## 📈 Funcionalidades Principais

- Autenticação JWT: Login seguro e stateless.
- Gestão de Usuários: Perfis distintos para ADMIN, Médico e Paciente.
- CRUD Completo: Para Médicos, Pacientes, Consultas e Especialidades.
- Gerenciamento de Consultas: Agendamento com validação de horários.
- Relatórios em PDF: Geração de relatórios de consultas por período.
- Validação de Dados: Uso de jakarta.validation para dados de entrada.

## 📈 Documentação dos endpoints via sawgger

http://localhost:8080/swagger-ui/index.html#/


📄 Licença
Este projeto está licenciado sob a [Julio Cesar De Souza Moura].
