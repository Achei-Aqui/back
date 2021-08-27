package br.com.fcamara.acheiaquiapi.controller.contato.form;

import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import org.apache.commons.lang3.EnumUtils;

public class AtualizarForm {

    private String nome;

    private String email;

    private String telefone;

    private String categoria;

    public Usuario atualizarUsuario(Usuario usuario) {
        System.out.println(email);
        if(nome == null || nome.equals("") || nome.length() < 4 || nome.length() > 100) {
            nome = usuario.getContato().getNome();
        }
        if(email == null || email.equals("") || !(email.matches("^[a-zA-Z0-9_!#$%&?*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))) {
            email = usuario.getEmail();
        }
        if(telefone == null || telefone.equals("") || !telefone.matches("^((\\(\\d{2}\\))|\\d{2})[- .]?(\\d{5}|\\d{4})[- .]?\\d{4}$")) {
            telefone = usuario.getContato().getTelefone();
        }
        if(!EnumUtils.isValidEnum(Categoria.class, categoria)) {
            categoria = usuario.getContato().getCategoria().name();
        }

        usuario.getContato().setNome(nome);
        usuario.setEmail(email);
        usuario.getContato().setTelefone(telefone);
        usuario.getContato().setCategoria(Enum.valueOf(Categoria.class, categoria));
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
