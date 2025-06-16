package biovitta.com.clinics.DTOs;

import biovitta.com.clinics.entities.Consulta;
import biovitta.com.clinics.entities.Paciente;
import biovitta.com.clinics.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

public class PacienteConsultaDTO {

    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private List<ConsultaDTO> consultaLista;

    public PacienteConsultaDTO(String nome, String email, String telefone, LocalDate dataNascimento, List<ConsultaDTO
            > consultaLista) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.consultaLista = consultaLista;
    }

    public PacienteConsultaDTO(Paciente entity) {
       nome = entity.getNome();
       email = entity.getEmail();
       telefone = entity.getTelefone();
       dataNascimento = entity.getDataNascimento();
       consultaLista = entity.getConsultas()
               .stream()
               .map(ConsultaDTO::new)
               .toList();;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<ConsultaDTO> getConsultaLista() {
        return consultaLista;
    }
}
