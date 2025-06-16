package biovitta.com.clinics.DTOs;

import biovitta.com.clinics.entities.Paciente;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class PacienteDTO {

    private Long pacienteId;
    private String nome;
    private String email;
    private String telefone;
    private String imgUrl;
    private LocalDate dataNascimento;

    public PacienteDTO(Long pacienteId, String nome, String email, String telefone, String imgUrl, LocalDate dataNascimento) {
        this.pacienteId = pacienteId;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imgUrl = imgUrl;
        this.dataNascimento = dataNascimento;
    }

    public PacienteDTO() {
    }

    public PacienteDTO(Paciente entity){
        pacienteId = entity.getPacienteId();;
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        imgUrl = entity.getImgUrl();
        dataNascimento = entity.getDataNascimento();
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
