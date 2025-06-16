package biovitta.com.clinics.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name =  "tb_paciente")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id", unique = true)
    private Long pacienteId;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "telefone", unique = true)
    private String telefone;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;


    @OneToOne
    @JoinColumn( name = "usuario_id")
    private Usuario usuario;

    public Paciente() {
    }

    public Paciente(Long pacienteId, String nome, String email, String telefone, String imgUrl, LocalDate dataNascimento) {
        this.pacienteId = pacienteId;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imgUrl = imgUrl;
        this.dataNascimento = dataNascimento;
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

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
