package br.com.fcamara.acheiaquiapi.controller;

import br.com.fcamara.acheiaquiapi.model.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatosController {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_COMPRADOR') or hasRole('ROLE_FORNECEDOR')")
    public List<Contato> listaDeContatos() {
        List<Contato> contatos = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAll();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        String role = userRole(username);

        for( Usuario usuario : usuarios ) {
            UserDetails details = userDetailsService.loadUserByUsername(usuario.getCnpj());
            if(details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role))) {
                contatos.add(usuario.getContato());
            }
        }
        return contatos;
    }

    public String userRole(String username) {
        UserDetails details = userDetailsService.loadUserByUsername(username);
        if(details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_COMPRADOR"))) {
            return "ROLE_FORNECEDOR";
        } else if(details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_FORNECEDOR"))) {
            return "ROLE_COMPRADOR";
        } else {
            throw new RuntimeException();
        }

    }
}
