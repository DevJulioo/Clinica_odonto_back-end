package biovitta.com.clinics.DTOs;

import biovitta.com.clinics.entities.Especialidades;

public class EspecialidadesDTO {

    private Long especialidade_id;
    private String nome;


    public EspecialidadesDTO(Long especialidade_id, String nome) {
        this.especialidade_id = especialidade_id;
        this.nome = nome;
    }

    public EspecialidadesDTO() {
    }

    public EspecialidadesDTO(Especialidades entity){
        especialidade_id = entity.getEspecialidadeId();
        nome = entity.getNome();
    }


    public Long getEspecialidade_id() {
        return especialidade_id;
    }

    public void setEspecialidade_id(Long especialidade_id) {
        this.especialidade_id = especialidade_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
