package br.com.fcamara.acheiaquiapi.controller.contato.dto;


import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import org.springframework.data.domain.Page;

public class ContatoDto {

    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private Categoria categoria;

    private EnderecoDto endereco;

    public ContatoDto(Usuario usuario) {
        this.nome = usuario.getContato().getNome();
        this.cnpj = usuario.getCnpj();
        this.email = usuario.getEmail();
        this.telefone = usuario.getContato().getTelefone();
        this.categoria = usuario.getContato().getCategoria();
        this.endereco = new EnderecoDto(usuario.getContato().getEndereco());
    }

    public static Page<ContatoDto> converter(Page<Usuario> contatos) {
        return contatos.map(ContatoDto::new);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEnderecoDto(EnderecoDto endereco) {
        this.endereco = endereco;
    }
}
