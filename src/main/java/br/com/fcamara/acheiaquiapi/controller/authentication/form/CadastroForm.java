package br.com.fcamara.acheiaquiapi.controller.authentication.form;


import br.com.fcamara.acheiaquiapi.config.validacao.ValidadorCNPJService;
import br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.TipoDeUsuarioInexistenteException;
import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.model.contato.Endereco;
import br.com.fcamara.acheiaquiapi.repository.authentication.PerfilRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CadastroForm {

    private String tipo;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$", message = "O cpnj deve possuir o padrão XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    @NotNull @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&?*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "O email deve possuir o padrão XXXXX@XXXX.XXX")
    private String email;

    @NotNull @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*])(?=\\S+$).{8,20}$",
            message = "A senha deve possuir pelo menos um numero, pelo menos uma letra minuscula, " +
                    "pelo menos uma letra maiscula, pelo menos um caracter especial, probibido espaços e possuir 8 a 20 caracteres")
    private String senha;

    @NotNull
    private Categoria categoria;

    @NotNull @NotBlank
    @Length(min = 4, max = 100, message = "O nome deve possuir entre 4 e 100 caracteres")
    private String nome;

    @NotNull @NotBlank
    @Pattern(regexp= "^((\\(\\d{2}\\))|\\d{2})[- .]?(\\d{5}|\\d{4})[- .]?\\d{4}$", message = "O telefone deve possuir o padrão XX XXXXX-XXXX ou XX XXXX-XXXX")
    private String telefone;
    @NotNull @NotBlank
    private String cep;
    @NotNull @NotBlank
    private String bairro;
    @NotNull @NotBlank
    private String cidade;
    @NotNull @NotBlank
    private String estado;
    @NotNull @NotBlank
    private String rua;

    @NotNull
    private String complemento;

    @NotNull @NotBlank
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
        if(tipoDeUsuario.isPresent() && (!tipoDeUsuario.get().getNome().equals("ROLE_ADMIN"))) {
            List<Perfil> perfis = new ArrayList<>();
            perfis.add(tipoDeUsuario.get());

            usuario.setContato(contato);
            usuario.setPerfis(perfis);
            return usuario;
        }
        throw new TipoDeUsuarioInexistenteException(); //EXCEPTION DE TIPO ERRADO COMPRADOR 0U VENDEDOR
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
