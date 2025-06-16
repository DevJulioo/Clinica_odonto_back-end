package biovitta.com.clinics.controllers;

import biovitta.com.clinics.DTOs.EspecialidadeRequestDTO;
import biovitta.com.clinics.DTOs.EspecialidadesDTO;

import biovitta.com.clinics.services.EspecialidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("biovitta/api/especialidades")
public class EspecialidadeController {

    @Autowired
    EspecialidadeService especialidadeService;

    @GetMapping("/get/all")
    public ResponseEntity<List<EspecialidadesDTO>> listarEspecialidades(){
        return ResponseEntity.ok(especialidadeService.listarEspecialidades());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EspecialidadesDTO> buscarEspecialidade(@PathVariable Long id){
        return ResponseEntity.ok(especialidadeService.buscarEspecialidade(id));
    }

    @PostMapping("/add")
    public ResponseEntity<EspecialidadesDTO> adicionarEspecialidade(@RequestBody @Valid EspecialidadeRequestDTO dto){
        return ResponseEntity.ok(especialidadeService.adicionarEspecialidade(dto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EspecialidadesDTO> editarEspecialidade(@PathVariable Long id, @RequestBody @Valid EspecialidadeRequestDTO dto){
        return ResponseEntity.ok(especialidadeService.editarEspecialidade(dto, id));
    }

    @DeleteMapping("/dell/{id}")
    public ResponseEntity<String> deletarEspecialidade(@PathVariable Long id){
        return ResponseEntity.ok(especialidadeService.deletarEspecialidade(id));
    }
}