package biovitta.com.clinics.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "tb_medico" )
public class Medico {

    @Id
    @Column(name = "crm", unique = true, length = 15)
    private String crm;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "telefone", unique = true)
    private String telefone;
    @Column(name = "img_url")
    private String imgUrl;


    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultasMedico;

    @ManyToMany
    @JoinTable(
            name = "tb_medico_especialidade",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private List<Especialidades> especialidades;


    @OneToOne
    @JoinColumn( name = "usuario_id")
    private Usuario usuario;

    public Medico() {
    }

    public Medico(String crm, String nome, String email, String telefone, String imgUrl) {
        this.crm = crm;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imgUrl = imgUrl;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
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

    public List<Consulta> getConsultasMedico() {
        return consultasMedico;
    }

    public void setConsultasMedico(List<Consulta> consultasMedico) {
        this.consultasMedico = consultasMedico;
    }

    public List<Especialidades> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
