package biovitta.com.clinics.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "tb_consulta")
public class Consulta {

    @Id @GeneratedValue( strategy =  GenerationType.IDENTITY)
    @Column(name =  "consulta_id", unique = true)
    private Long consultaId;
    @Column(name =  "data_consulta")
    private LocalDateTime dataConsulta;


    @ManyToOne
    @JoinColumn(name =  "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidades especialidade;


    public Consulta() {
    }
    public Consulta(Long consultaId, LocalDateTime dataConsulta) {
        this.consultaId = consultaId;
        this.dataConsulta = dataConsulta;
    }

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidades getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidades especialidade) {
        this.especialidade = especialidade;
    }
}