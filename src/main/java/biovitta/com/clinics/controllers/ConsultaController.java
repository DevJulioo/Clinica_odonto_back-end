package biovitta.com.clinics.controllers;

import biovitta.com.clinics.DTOs.ConsultaDTO;
import biovitta.com.clinics.DTOs.PacienteConsultaDTO;
import biovitta.com.clinics.DTOs.cadastro.ConsultaRequestDTO;
import biovitta.com.clinics.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("biovitta/api/consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @PostMapping("/add")
    public ResponseEntity<ConsultaRequestDTO> novaConsulta(@RequestBody ConsultaRequestDTO dto){
        return ResponseEntity.ok(consultaService.adicionarConsulta(dto));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<PacienteConsultaDTO> consultasPorPaciente(@PathVariable Long id){
        return ResponseEntity.ok(consultaService.consultaPaciente(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ConsultaDTO>> listarConsultas(){
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ConsultaDTO> buscarConsulta(@PathVariable Long id){
        return ResponseEntity.ok(consultaService.buscarConsulta(id));
    }

    @DeleteMapping("/dell/{id}")
    public ResponseEntity<String> deletarConsulta(@PathVariable Long id){
        return ResponseEntity.ok(consultaService.deletarConsulta(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ConsultaDTO> editarConsulta(@PathVariable Long id, @RequestBody ConsultaDTO dto){
        return ResponseEntity.ok(consultaService.editarConsulta(dto, id));
    }
}