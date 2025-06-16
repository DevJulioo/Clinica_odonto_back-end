package biovitta.com.clinics.services;

import biovitta.com.clinics.DTOs.PacienteDTO;
import biovitta.com.clinics.DTOs.cadastro.PacienteRequestDTO;
import biovitta.com.clinics.entities.Paciente;
import biovitta.com.clinics.entities.Usuario;
import biovitta.com.clinics.repositories.PacienteRepositorio;
import biovitta.com.clinics.repositories.UsuarioRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    PacienteRepositorio pacienteRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    PasswordEncoder config;


    @Transactional
    public PacienteDTO editarPaciente(PacienteRequestDTO dto, Long id){
        Paciente paciente = pacienteRepositorio.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com ID: " + id));
        Optional.ofNullable(dto.getNome()).filter(s -> !s.isBlank()).ifPresent(paciente::setNome);
        Optional.ofNullable(dto.getEmail()).filter(s -> !s.isBlank()).ifPresent(paciente::setEmail);
        Optional.ofNullable(dto.getImgUrl()).filter(s -> !s.isBlank()).ifPresent(paciente::setImgUrl);
        Optional.ofNullable(dto.getTelefone()).filter(s -> !s.isBlank()).ifPresent(paciente::setTelefone);
        Optional.ofNullable(dto.getDataNascimento()).ifPresent(paciente::setDataNascimento);
            paciente = pacienteRepositorio.save(paciente);

            Usuario usuario = usuarioRepositorio.findById(paciente.getUsuario().getUsuarioId()).get();
            Optional.ofNullable(dto.getSenha())
                .filter(s -> !s.isBlank())
                .map(config::encode)
                .ifPresent(usuario::setSenha);
            usuario = usuarioRepositorio.save(usuario);

            return new PacienteDTO(paciente);
    }


    public List<PacienteDTO> listarPacientes() {
        return pacienteRepositorio.findAll()
                .stream()
                .map(PacienteDTO::new)
                .collect(Collectors.toList());
    }

    public PacienteDTO buscarPaciente(Long id){
        Paciente paciente = pacienteRepositorio.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com ID: " + id));
        return new PacienteDTO(paciente);
    }


    @Transactional
    public String deletarPaciente(Long id){
        Paciente paciente = pacienteRepositorio.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com ID: " + id));

        usuarioRepositorio.deleteById(paciente.getUsuario().getUsuarioId());
        pacienteRepositorio.deleteById(id);

        return "Paciente deletado com sucesso!";
    }



}
