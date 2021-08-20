package br.com.fcamara.acheiaquiapi.controller;

import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.repository.ContatoRepository;
import br.com.fcamara.acheiaquiapi.repository.EnderecoRepository;
import br.com.fcamara.acheiaquiapi.repository.PerfilRepository;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatosController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public List<Contato> listaDeContatos() {

        List<Contato> contatos = new ArrayList<>();

        // Descobre o nome do usuario logado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        // Descobre o tipo de usuario dos outros usuarios logados
        Perfil tipoDosOutrosUsuarios = tipoDeUsuarioDosOutrosContatos(username);

        // Cria a lista dos perfis desejados usando o tipo do usuario descoberto anteriormente
        List<Perfil> perfis = new ArrayList<>();
        Perfil perfil = new Perfil(tipoDosOutrosUsuarios.getId(), tipoDosOutrosUsuarios.getNome());
        perfis.add(perfil);

        // Faz um for para adicionar apenas os contatos de cada usuario do perfil desejado
        List<Usuario> usuarios = usuarioRepository.findAllByPerfisIn(perfis);
        for ( Usuario usuario :
             usuarios ) {
            contatos.add(usuario.getContato());
        }

        return contatos;
    }


    public Perfil tipoDeUsuarioDosOutrosContatos(String username) {
            Optional<Usuario> optional = usuarioRepository.findBycnpj(username);
            String role = optional.get().getPerfis().get(0).toString();

            if(role.equals("ROLE_COMPRADOR")) {
                Optional<Perfil> perfilOptional = perfilRepository.findByNome("ROLE_FORNECEDOR");
                return perfilOptional.get();
            }

            Optional<Perfil> perfilOptional = perfilRepository.findByNome("ROLE_COMPRADOR");
            return perfilOptional.get();
    }

    @DeleteMapping
    public ResponseEntity<?> deletarSeuContato() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Optional<Usuario> optional = usuarioRepository.findBycnpj(username);
        if(optional.isPresent()) {
            String cnpj = optional.get().getCnpj();
            Usuario usuario = optional.get();

            enderecoRepository.deleteById(usuario.getContato().getEndereco().getId());
            contatoRepository.deleteById(usuario.getContato().getId());
            usuarioRepository.deleteById(usuario.getId());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
