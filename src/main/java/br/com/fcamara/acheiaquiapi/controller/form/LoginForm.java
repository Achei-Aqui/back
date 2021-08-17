package br.com.fcamara.acheiaquiapi.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String cnpj;
    private String senha;

    public String getCnpj() {
        return cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(cnpj, senha);
    }
}
