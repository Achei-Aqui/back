package br.com.fcamara.acheiaquiapi.controller.authentication.dto;


import br.com.fcamara.acheiaquiapi.controller.contato.dto.ContatoDto;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;

public class UsuarioDto {

    private ContatoDto contato;
    private String role;

    public UsuarioDto(Usuario usuario) {
        this.contato = new ContatoDto(usuario);
        this.role = usuario.getPerfis().get(0).getNome();
    }

    public ContatoDto getContato() {
        return contato;
    }

    public String getRole() {
        return role;
    }
}
