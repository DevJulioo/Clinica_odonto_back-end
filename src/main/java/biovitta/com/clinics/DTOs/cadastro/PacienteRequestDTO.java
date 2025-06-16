package biovitta.com.clinics.DTOs.cadastro;

import biovitta.com.clinics.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat; // <-- IMPORT ADICIONADO
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PacienteRequestDTO {

    private String nome;
    private String email;
    private String telefone;
    private String imgUrl;

    @JsonFormat(pattern = "dd/MM/yyyy") // <-- LINHA ADICIONADA
    private LocalDate dataNascimento;
    
    private String senha;

    public PacienteRequestDTO(String nome, String email, String telefone, String imgUrl, LocalDate dataNascimento, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imgUrl = imgUrl;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public PacienteRequestDTO() {
    }

    public PacienteRequestDTO(Paciente entity){
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        imgUrl = entity.getImgUrl();
        dataNascimento = entity.getDataNascimento();
        senha = null;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}