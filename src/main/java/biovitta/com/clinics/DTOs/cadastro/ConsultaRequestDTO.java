package biovitta.com.clinics.DTOs.cadastro;


import biovitta.com.clinics.entities.Consulta;

import java.time.LocalDateTime;

public class ConsultaRequestDTO {

    private Long consultaId;
    private LocalDateTime dataConsulta;
    private Long pacienteId;
    private String medicoId;

    public ConsultaRequestDTO(Long consultaId, LocalDateTime dataConsulta, Long pacienteId, String medicoId) {
        this.consultaId = consultaId;
        this.dataConsulta = dataConsulta;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
    }

    public ConsultaRequestDTO() {
    }

    public ConsultaRequestDTO(Consulta entity){
        consultaId = entity.getConsultaId();
        dataConsulta = entity.getDataConsulta();
        pacienteId = entity.getPaciente().getPacienteId();
        medicoId = entity.getMedico().getCrm();
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

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }

	public Long getEspecialidadeId() {
		// TODO Auto-generated method stub
		return null;
	}
}
