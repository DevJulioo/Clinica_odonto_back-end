package biovitta.com.clinics.repositories;

import biovitta.com.clinics.entities.Especialidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadeRepositorio extends JpaRepository<Especialidades, Long> {
}
