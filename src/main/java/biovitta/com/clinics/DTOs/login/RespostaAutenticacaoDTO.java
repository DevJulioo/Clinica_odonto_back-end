package biovitta.com.clinics.DTOs.login;

import biovitta.com.clinics.entities.Permissao;
import biovitta.com.clinics.entities.Usuario;

public class RespostaAutenticacaoDTO {
  //Classe para retornar o token de acesso ao sistema
    private String token;
    private String role;
    private String usuario;


    public RespostaAutenticacaoDTO(String token, Usuario entity) {
        this.token = token;
        this.role = entity.getRole().toString()  ;
        this.usuario = entity.getUsuario() ;
    }

    // Para definir o TOKEN!
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getUsuario() {
        return usuario;
    }
}
