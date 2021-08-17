package br.com.fcamara.acheiaquiapi.controller;

import br.com.fcamara.acheiaquiapi.model.Usuario;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatosController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    @ResponseBody
    public void pai() {
        Optional<Usuario> byId = repository.findById(1L);
        System.out.println(byId);
        Optional<Usuario> comprador = repository.findBycnpj("comprador");
        System.out.println(comprador);
    }
}
