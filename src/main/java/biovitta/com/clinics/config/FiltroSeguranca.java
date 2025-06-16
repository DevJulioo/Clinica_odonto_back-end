package biovitta.com.clinics.config;

import biovitta.com.clinics.repositories.UsuarioRepositorio;
import biovitta.com.clinics.services.autenticacao.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Exclui as rotas públicas da validação de token
        if (request.getRequestURI().equals("/biovitta/auth/login") ||
            request.getRequestURI().equals("/biovitta/auth/register") ||
            request.getRequestURI().equals("/biovitta/api/usuario/paciente/add") ||
            request.getRequestURI().equals("/biovitta/api/usuario/medico/add")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);
        if(token != null){
            var returnUsuario = tokenService.validarToken(token);
            // Certifica-se de que o usuário existe antes de tentar autenticá-lo
            if (returnUsuario != null && !returnUsuario.isEmpty()) {
                UserDetails usuario = usuarioRepositorio.findByUsuario(returnUsuario);

                // Apenas autentica se o UserDetails foi encontrado
                if (usuario != null) {
                    var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private  String recoverToken(HttpServletRequest request){
        var HeaderAuth = request.getHeader("Authorization");
        if(HeaderAuth == null) return null;
        return HeaderAuth.replace("Bearer ", "");
    }
}