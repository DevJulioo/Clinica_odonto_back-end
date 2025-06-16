package biovitta.com.clinics.DTOs.cadastro;

import biovitta.com.clinics.entities.Medico;

import java.util.List;

public class MedicoRequestDTO {

    private String crm;
    private String nome;
    private String email;
    private String telefone;
    private String imgUrl;
    private String senha;
    private List<Long> especialidadesIds;


    public MedicoRequestDTO(String crm, String nome, String email, String telefone,
                            String imgUrl, String senha, List<Long> especialidadesIds) {
        this.crm = crm;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imgUrl = imgUrl;
        this.senha = senha;
        this.especialidadesIds = especialidadesIds;
    }

    public MedicoRequestDTO(Medico entity){
        crm = entity.getCrm();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        imgUrl = entity.getImgUrl();
        senha = null;
        especialidadesIds = entity.getEspecialidades()
                .stream()
                .map(especialidade -> especialidade.getEspecialidadeId())
                .toList();
    }

    public MedicoRequestDTO() {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Long> getEspecialidadesIds() {
        return especialidadesIds;
    }

    public void setEspecialidadesIds(List<Long> especialidadesIds) {
        this.especialidadesIds = especialidadesIds;
    }
}
