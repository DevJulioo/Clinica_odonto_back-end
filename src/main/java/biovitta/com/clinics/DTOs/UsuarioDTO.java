package biovitta.com.clinics.DTOs;

import biovitta.com.clinics.entities.Permissao;
import biovitta.com.clinics.entities.Usuario;

public class UsuarioDTO {

    private Long usuarioId;
    private String usuario;
    private String senha;
    private String role;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long usuarioId, String usuario, String senha, String role) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.senha = senha;
        this.role = role;
    }

    public UsuarioDTO(Usuario entity){
        usuarioId = entity.getUsuarioId();
        usuario = entity.getUsuario();
        senha = entity.getSenha();
        role = entity.getRole().toString();


    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
