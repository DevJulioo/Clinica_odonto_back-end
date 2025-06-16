package biovitta.com.clinics.controllers;

import biovitta.com.clinics.DTOs.MedicoEspecialidadesDTO;
import biovitta.com.clinics.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("biovitta/api/medicos")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping("/{crm}/especialidades")
    public ResponseEntity<MedicoEspecialidadesDTO> medicoEspecialidades(@PathVariable String crm){
        return ResponseEntity.ok(medicoService.medicoEspecialidades(crm));
    }
}
