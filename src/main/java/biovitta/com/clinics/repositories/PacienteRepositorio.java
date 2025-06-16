package biovitta.com.clinics.repositories;

import biovitta.com.clinics.entities.Paciente;
import biovitta.com.clinics.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
