# Gerenciando as Rotas:

`http://localhost:8080/`

### Autenticação Controller: `/biovitta/auth`

- POST(login + token): http://localhost:8080/biovitta/auth/login

```JSON
{
  "usuario": "mariaclara.souza@example.com",
  "senha": "senhaSegura123"
}
   ```

- POST(registrar | paciente): http://localhost:8080/biovitta/auth/register

```JSON
{
  "nome": "Maria Clara Souza",
  "email": "mariaclara.souza@example.com",
  "telefone": "(11) 91234-5678",
  "imgUrl": "https://example.com/imagens/mariaclara.jpg",
  "dataNascimento": "2001-08-15",
  "senha": "senhaSegura123"
}
   ```

### Usuario Controller: `biovitta/api/usuario`

#### -Paciente:
- POST(registrar paciente): http://localhost:8080/biovitta/api/usuario/paciente/add
```JSON
{
  "nome": "Maria Clara Souza",
  "email": "mariaclara.souza@example.com",
  "telefone": "(11) 91234-5678",
  "imgUrl": "https://example.com/imagens/mariaclara.jpg",
  "dataNascimento": "2001-08-15",
  "senha": "senhaSegura123"
}
   ```
- GET (todos pacientes): http://localhost:8080/biovitta/api/usuario/paciente/get/all
- GET (paciente por ID): http://localhost:8080/biovitta/api/usuario/paciente/get/{id}
- DELETE (paciente por ID): http://localhost:8080/biovitta/api/usuario/paciente/dell/{id}
- PUT(paciente por ID): http://localhost:8080/biovitta/api/usuario/paciente/edit/{id}

#### -Medico:
- POST(registrar médico ): http://localhost:8080/biovitta/api/usuario/medico/add

```JSON
{
  "crm": "123456-SP",
  "nome": "Dr. João Pedro Lima",
  "email": "joao.lima@clinicamedica.com",
  "telefone": "(11) 99876-5432",
  "imgUrl": "https://example.com/imagens/joaopedro.jpg",
  "senha": "medicoForte2025",
  "especialidadesIds": [1, 3, 5]
}
   ```
- GET(todos médicos): http://localhost:8080/biovitta/api/usuario/medico/get/all
- GET(Médico por ID): http://localhost:8080/biovitta/api/usuario/medico/get/{crm}
- DELETE(Médico por ID): http://localhost:8080/biovitta/api/usuario/medico/dell/{crm}
- PUT(Médico por ID): http://localhost:8080/biovitta/api/usuario/medico/edit/{crm}

##### -Usuário(ADM):
- POST(registrar adm): http://localhost:8080/biovitta/api/usuario/adm/add
```JSON
{
  "usuario": "admin123",
  "senha": "senhaSuperSegura!"
}
```

### Consulta Controller: `biovitta/api/consulta`
- POST(consulta):  http://localhost:8080/biovitta/api/consulta/add
```JSON
{
  "dataConsulta": "2025-06-10T14:30:00",
  "pacienteId": 1,
  "medicoId": "123456-SP"
}
```
- GET(consulta por paciente):  http://localhost:8080/biovitta/api/consulta/paciente/{id}


### Medico Controller: `biovitta/api/medicos`
- GET(especialidades por médico): http://localhost:8080/biovitta/api/medicos/{crm}/especialidades

### O que há de fazer:
- [ ] Retornar Especialidades | Controller e Service com os métodos
- [ ] Consultas - Service/Controller - Editar | Deletar | GET todas as consultas!
- [ ] TESTES POSTMAN
- [ ] Definir os níveis de Acesso!
- [ ] Geração de Relatório | Service + DTO + Controller