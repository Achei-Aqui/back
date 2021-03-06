package br.com.fcamara.acheiaquiapi.controller;

import br.com.fcamara.acheiaquiapi.model.Perfil;
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

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatosController {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Contato> listaDeContatos() {

        List<Contato> contatos = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAll();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        //System.out.println(optional.get().getPerfis().get(0).toString()); -> ROLE

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
            Optional<Usuario> optional = usuarioRepository.findBycnpj(username);
            String role = optional.get().getPerfis().get(0).toString();

            if(role.equals("ROLE_COMPRADOR")) {
                return "ROLE_FORNECEDOR";
            }
            return "ROLE_COMPRADOR";
    }
}
