package biovitta.com.clinics.DTOs;

import biovitta.com.clinics.entities.Especialidades;

public class EspecialidadeRequestDTO {

	private String nome;

    public EspecialidadeRequestDTO() {
    }

    public EspecialidadeRequestDTO(String nome) {
        this.nome = nome;
    }

    public EspecialidadeRequestDTO(Especialidades entity){
        this.nome = entity.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


