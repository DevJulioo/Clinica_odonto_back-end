package biovitta.com.clinics.repositories;

import biovitta.com.clinics.entities.Paciente;
import biovitta.com.clinics.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByUsuario(String usuario);

    Paciente deleteByUsuario(String usuario);

}
