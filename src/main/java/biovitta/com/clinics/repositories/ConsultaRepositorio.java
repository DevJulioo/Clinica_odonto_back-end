package biovitta.com.clinics.repositories;

import biovitta.com.clinics.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List; 

public interface ConsultaRepositorio extends JpaRepository<Consulta, Long> {

    boolean existsByMedico_CrmAndDataConsulta(String crm, LocalDateTime dataConsulta);

    List<Consulta> findByDataConsultaBetween(LocalDateTime dataInicio, LocalDateTime dataFim); // Novo método para relatório
}