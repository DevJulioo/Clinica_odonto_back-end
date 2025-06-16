package biovitta.com.clinics.repositories;

import biovitta.com.clinics.entities.Medico;
import biovitta.com.clinics.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, String> {

    boolean existsByEmail(String email);

    Usuario findByEmail(String email);

}
