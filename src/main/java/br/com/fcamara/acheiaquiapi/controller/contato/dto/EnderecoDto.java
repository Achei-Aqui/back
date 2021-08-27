package br.com.fcamara.acheiaquiapi.controller.contato.dto;

import br.com.fcamara.acheiaquiapi.model.contato.Endereco;

public class EnderecoDto {

    private String cep;
    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String estado;
    private String complemento;

    public EnderecoDto(Endereco endereco) {
        this.cep = endereco.getCep();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.bairro = endereco.getBairro();
        this.estado = endereco.getEstado();
        this.complemento = endereco.getComplemento();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
