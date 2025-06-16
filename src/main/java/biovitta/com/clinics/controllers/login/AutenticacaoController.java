package biovitta.com.clinics.controllers.login;

import biovitta.com.clinics.DTOs.cadastro.PacienteRequestDTO;
import biovitta.com.clinics.DTOs.login.AutenticacaoDTO;
import biovitta.com.clinics.services.UsuarioService;
import biovitta.com.clinics.services.autenticacao.AutenticacaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "biovitta/auth")
public class AutenticacaoController {

    // Adicionando um logger
    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AutenticacaoDTO dados ){
        logger.info(">>> CONTROLLER: Requisição recebida em /login. <<<");
        return ResponseEntity.ok(autenticacaoService.login(dados));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody @Valid PacienteRequestDTO dto){
        // MENSAGEM DE DIAGNÓSTICO 3
        logger.info(">>> CONTROLLER: Requisição recebida em /register. O MÉTODO FOI ALCANÇADO! <<<");
        try {
            var pacienteSalvo = usuarioService.novoPaciente(dto);
            return  ResponseEntity.ok(pacienteSalvo);
        } catch (Exception e) {
            logger.error("### ERRO AO TENTAR SALVAR PACIENTE: {} ###", e.getMessage());
            return ResponseEntity.internalServerError().body("Erro interno ao registrar paciente.");
        }
    }
}