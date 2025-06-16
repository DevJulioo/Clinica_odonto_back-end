package biovitta.com.clinics.controllers;

import biovitta.com.clinics.DTOs.MedicoDTO;
import biovitta.com.clinics.DTOs.PacienteDTO;
import biovitta.com.clinics.DTOs.UsuarioDTO;
import biovitta.com.clinics.DTOs.cadastro.MedicoRequestDTO;
import biovitta.com.clinics.DTOs.cadastro.PacienteRequestDTO;
import biovitta.com.clinics.entities.Paciente;
import biovitta.com.clinics.repositories.MedicoRepositorio;
import biovitta.com.clinics.services.MedicoService;
import biovitta.com.clinics.services.PacienteService;
import biovitta.com.clinics.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("biovitta/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    MedicoService medicoService;


        @PostMapping("/paciente/add")
    public ResponseEntity<PacienteDTO> adicionarPaciente(@RequestBody @Valid PacienteRequestDTO dto){
        return ResponseEntity.ok(usuarioService.novoPaciente(dto));
    }

    @PostMapping("/medico/add")
    public  ResponseEntity<MedicoDTO> adicionarMedico(@RequestBody @Valid MedicoRequestDTO dto){
        return ResponseEntity.ok(usuarioService.novoMedico(dto));
    }

    @PostMapping("adm/add")
    public ResponseEntity<?> adicionarAdministrador(@RequestBody @Valid UsuarioDTO dto){
        return ResponseEntity.ok(usuarioService.novoAdministrador(dto));
    }

    @GetMapping("paciente/get/all")
    public ResponseEntity<List<PacienteDTO>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("paciente/get/{id}")
    public ResponseEntity<PacienteDTO> buscarPaciente(@PathVariable Long id){
            return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }

    @DeleteMapping("paciente/dell/{id}")
    public  String deletarPaciente(@PathVariable Long id){
            return pacienteService.deletarPaciente(id);
    }

    @PutMapping("paciente/edit/{id}")
    public  ResponseEntity<PacienteDTO>  editarPaciente(@PathVariable Long id, @RequestBody PacienteRequestDTO dto){
            return ResponseEntity.ok(pacienteService.editarPaciente(dto, id));
    }

    @GetMapping("medico/get/all")
    public  ResponseEntity<List<MedicoDTO>> listarMedicos(){
            return ResponseEntity.ok(medicoService.listarMedicos());
    }

    @GetMapping("medico/get/{crm}")
    public ResponseEntity<MedicoDTO> buscarMedico(@PathVariable String crm){
            return ResponseEntity.ok(medicoService.buscarMedico(crm));
    }

    @DeleteMapping("medico/dell/{crm}")
    public String deletarMedico(@PathVariable String crm){
            return medicoService.deletarMedico(crm);
    }

    @PutMapping("medico/edit/{crm}")
    public ResponseEntity<MedicoDTO> editarMedico(@PathVariable String crm, @RequestBody MedicoRequestDTO dto){
            return ResponseEntity.ok(medicoService.editarMedico(dto, crm));
    }
}
