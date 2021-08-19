package br.com.fcamara.acheiaquiapi.controller.authentication.form;


import br.com.fcamara.acheiaquiapi.config.validacao.ValidadorCNPJService;
import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.model.contato.Endereco;
import br.com.fcamara.acheiaquiapi.repository.PerfilRepository;
import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CadastroForm {

    private String tipo;

    @NotNull
    @Pattern(regexp = "^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$")
    private String cnpj;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&?*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String senha;

    @NotNull
    private Categoria categoria;
    @NotNull
    private String nome;

    @NotNull
    @Pattern(regexp="^((\\(\\d{2}\\))|\\d{2})[- .]?\\d{5}[- .]?\\d{4}$")
    private String telefone;

    @NotNull
    private String cep;
    @NotNull
    private String bairro;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String rua;

    @NotNull
    private String complemento;

    private String numero;

    public Endereco criarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setRua(rua);
        endereco.setComplemento(complemento);
        endereco.setNumero(numero);
        return endereco;
    }

    public Contato criarContato(Endereco endereco) {
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setTelefone(telefone);
        contato.setCategoria(categoria);
        contato.setEndereco(endereco);
        return contato;
    }

    public Usuario criarUsuario(Contato contato, PerfilRepository perfilRepository) {
        Usuario usuario = new Usuario();
        ValidadorCNPJService.validar(cnpj);
        usuario.setCnpj(cnpj);
        usuario.setEmail(email);

        usuario.setSenha(
                encriptarSenha(senha)
        );

        Optional<Perfil> tipoDeUsuario = perfilRepository.findByNome(tipo);
        if(tipoDeUsuario.isPresent()) {
            List<Perfil> perfis = new ArrayList<>();
            perfis.add(tipoDeUsuario.get());

            usuario.setContato(contato);
            usuario.setPerfis(perfis);
            return usuario;
        }
        throw new RuntimeException(); //EXCEPTION DE TIPO ERRADO
    }

    public String encriptarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {

        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
