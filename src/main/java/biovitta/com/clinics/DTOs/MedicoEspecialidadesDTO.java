package biovitta.com.clinics.DTOs;

import biovitta.com.clinics.entities.Especialidades;
import biovitta.com.clinics.entities.Medico;

import java.util.List;

public class MedicoEspecialidadesDTO {

    private String crm;
    private String nome;
    private String email;
    private String telefone;
    private String imgUrl;
    private List<EspecialidadesDTO> especialidadesLista;


    public MedicoEspecialidadesDTO(String crm, String nome, String email, String telefone, String imgUrl, List<EspecialidadesDTO> especialidadesLista) {
        this.crm = crm;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imgUrl = imgUrl;
        this.especialidadesLista = especialidadesLista;
    }

    public MedicoEspecialidadesDTO() {
    }

    public MedicoEspecialidadesDTO(Medico entity){
        crm = entity.getCrm();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        imgUrl = entity.getImgUrl();
        especialidadesLista = entity.getEspecialidades()
                .stream()
                .map(EspecialidadesDTO::new)
                .toList();
    }

    public String getCrm() {
        return crm;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public List<EspecialidadesDTO> getEspecialidadesLista() {
        return especialidadesLista;
    }

}
