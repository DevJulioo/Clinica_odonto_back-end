package biovitta.com.clinics.services;

import biovitta.com.clinics.DTOs.EspecialidadeRequestDTO;
import biovitta.com.clinics.DTOs.EspecialidadesDTO;

import biovitta.com.clinics.entities.Especialidades;
import biovitta.com.clinics.repositories.EspecialidadeRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {

    @Autowired
    EspecialidadeRepositorio especialidadeRepositorio;

    public List<EspecialidadesDTO> listarEspecialidades(){
        return especialidadeRepositorio.findAll()
                .stream()
                .map(EspecialidadesDTO::new)
                .collect(Collectors.toList());
    }

    public EspecialidadesDTO buscarEspecialidade(Long id){
        Especialidades especialidade = especialidadeRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada com o ID: " + id));
        return new EspecialidadesDTO(especialidade);
    }

    @Transactional
    public EspecialidadesDTO adicionarEspecialidade(EspecialidadeRequestDTO dto){
        Especialidades especialidade = new Especialidades();
        especialidade.setNome(dto.getNome());
        especialidade = especialidadeRepositorio.save(especialidade);
        return new EspecialidadesDTO(especialidade);
    }

    @Transactional
    public EspecialidadesDTO editarEspecialidade(EspecialidadeRequestDTO dto, Long id){
        Especialidades especialidade = especialidadeRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada com o ID: " + id));
        Optional.ofNullable(dto.getNome()).filter(s -> !s.isBlank()).ifPresent(especialidade::setNome);
        especialidade = especialidadeRepositorio.save(especialidade);
        return new EspecialidadesDTO(especialidade);
    }

    @Transactional
    public String deletarEspecialidade(Long id){
        Especialidades especialidade = especialidadeRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada com o ID: " + id));
        especialidadeRepositorio.deleteById(id);
        return "Especialidade deletada com sucesso!";
    }
}