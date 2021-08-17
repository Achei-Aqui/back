package br.com.fcamara.acheiaquiapi.controller;

import br.com.fcamara.acheiaquiapi.model.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.repository.ContatoRepository;
import br.com.fcamara.acheiaquiapi.repository.EnderecoRepository;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/compradores")
public class CompradoresController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    

    @GetMapping
    public List<Contato> listaDeCompradores() {
        List<Contato> contatos = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAll();
        for ( Usuario usuario : usuarios ) {
            UserDetails details = userDetailsService.loadUserByUsername(usuario.getCnpj());
            if(details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_COMPRADOR"))) {
                contatos.add(usuario.getContato());
            }
        }
        return contatos;
    }
}
