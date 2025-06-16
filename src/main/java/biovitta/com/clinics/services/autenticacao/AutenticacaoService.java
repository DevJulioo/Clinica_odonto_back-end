package biovitta.com.clinics.services.autenticacao;

import biovitta.com.clinics.DTOs.login.AutenticacaoDTO;
import biovitta.com.clinics.DTOs.login.RespostaAutenticacaoDTO;
import biovitta.com.clinics.entities.Usuario;
import biovitta.com.clinics.repositories.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AutenticacaoService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    TokenService tokenService;

    public RespostaAutenticacaoDTO login(@Valid AutenticacaoDTO dados ){
        var credenciaisUsuario = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.senha());
        var auth = this.authenticationManager.authenticate(credenciaisUsuario);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        var usuario = (Usuario) auth.getPrincipal();
        return new RespostaAutenticacaoDTO(token, usuario);
    }

}
