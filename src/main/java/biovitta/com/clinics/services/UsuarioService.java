package biovitta.com.clinics.services;

import biovitta.com.clinics.DTOs.MedicoDTO;
import biovitta.com.clinics.DTOs.PacienteDTO;
import biovitta.com.clinics.DTOs.UsuarioDTO;
import biovitta.com.clinics.DTOs.cadastro.MedicoRequestDTO;
import biovitta.com.clinics.DTOs.cadastro.PacienteRequestDTO;
import biovitta.com.clinics.entities.*;
import biovitta.com.clinics.repositories.EspecialidadeRepositorio;
import biovitta.com.clinics.repositories.MedicoRepositorio;
import biovitta.com.clinics.repositories.PacienteRepositorio;
import biovitta.com.clinics.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    PacienteRepositorio pacienteRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    MedicoRepositorio medicoRepositorio;

    @Autowired
    EspecialidadeRepositorio especialidadeRepositorio;

    @Autowired
    PasswordEncoder config;

    @Transactional
    public PacienteDTO novoPaciente(PacienteRequestDTO dto){
        if(pacienteRepositorio.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email em uso!");
        }
        Paciente novoPaciente = new Paciente();
        novoPaciente.setNome(dto.getNome());
        novoPaciente.setEmail(dto.getEmail());
        novoPaciente.setTelefone(dto.getTelefone());
        novoPaciente.setDataNascimento(dto.getDataNascimento());
        novoPaciente.setImgUrl(dto.getImgUrl());

        Usuario usuario = new Usuario();
        usuario.setRole(Permissao.PACIENTE);
        usuario.setUsuario(dto.getEmail());
        usuario.setSenha(config.encode(dto.getSenha()));

        usuario = usuarioRepositorio.save(usuario);
        novoPaciente.setUsuario(usuario); // SETANDO O USUÁRIO!!!
        novoPaciente = pacienteRepositorio.save(novoPaciente);


        return  new PacienteDTO(novoPaciente);
    }

    @Transactional
    public MedicoDTO novoMedico(MedicoRequestDTO dto){
        if(medicoRepositorio.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email em uso!");
        }
        Medico novoMedico = new Medico();
        novoMedico.setCrm(dto.getCrm());
        novoMedico.setNome(dto.getNome());
        novoMedico.setEmail(dto.getEmail());
        novoMedico.setTelefone(dto.getTelefone());
        novoMedico.setImgUrl(dto.getImgUrl());

        List<Especialidades> especialidades = especialidadeRepositorio
                .findAllById(dto.getEspecialidadesIds());

        novoMedico.setEspecialidades(especialidades);

        Usuario usuario = new Usuario();
        usuario.setRole(Permissao.MEDICO);
        usuario.setUsuario(dto.getEmail());
        usuario.setSenha(config.encode(dto.getSenha()));

        usuario = usuarioRepositorio.save(usuario);
        novoMedico.setUsuario(usuario); //SETANDO O USUÁRIO
        novoMedico = medicoRepositorio.save(novoMedico);

        return  new MedicoDTO(novoMedico);
    }

    public String novoAdministrador(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setRole(Permissao.ADMIN);
        usuario.setUsuario(dto.getUsuario());
        usuario.setSenha(config.encode(dto.getSenha()));
        usuario = usuarioRepositorio.save(usuario);
        return "Novo usuário(ADM) criado com sucesso!";
    }


}
