package biovitta.com.clinics.DTOs;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class RelatorioConsultaDTO {
	
	private Long consultaId;
    private LocalDateTime dataConsulta;
    private String pacienteNome;
    private String medicoNome;
    private String especialidadeNome;

    public RelatorioConsultaDTO(Long consultaId, LocalDateTime dataConsulta, String pacienteNome, String medicoNome, String especialidadeNome) {
        this.consultaId = consultaId;
        this.dataConsulta = dataConsulta;
        this.pacienteNome = pacienteNome;
        this.medicoNome = medicoNome;
        this.especialidadeNome = especialidadeNome;
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

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
    }

    public String getEspecialidadeNome() {
        return especialidadeNome;
    }

    public void setEspecialidadeNome(String especialidadeNome) {
        this.especialidadeNome = especialidadeNome;
    }
}


