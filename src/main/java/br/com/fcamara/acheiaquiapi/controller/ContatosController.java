package br.com.fcamara.acheiaquiapi.controller;

import br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.CategoriaInexistenteException;
import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.repository.ContatoRepository;
import br.com.fcamara.acheiaquiapi.repository.EnderecoRepository;
import br.com.fcamara.acheiaquiapi.repository.PerfilRepository;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

        List<Perfil> perfis = construirListaDePerfilUsandoUsuarioLogado();

        List<Usuario> usuarios = usuarioRepository.findAllByPerfisIn(perfis);

        for ( Usuario usuario :
             usuarios ) {
            contatos.add(usuario.getContato());
        }

        return contatos;
    }

    @GetMapping("/{categoria}")
    public List<Contato> listaDeContatosFiltradaPorCategoria(@PathVariable String categoria) {
        List<Contato> contatos = new ArrayList<>();

        try {
            Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase(Locale.ROOT));

            List<Perfil> perfis = construirListaDePerfilUsandoUsuarioLogado();

            List<Usuario> usuariosPorCategoria = usuarioRepository.findAllByPerfisInAndContato_Categoria(perfis, categoriaEnum);

            for (Usuario usuario:
                    usuariosPorCategoria) {
                contatos.add(usuario.getContato());
            }

            return contatos;

        } catch(IllegalArgumentException ex) {

            throw new CategoriaInexistenteException();

        }

    }

    public List<Perfil> construirListaDePerfilUsandoUsuarioLogado() {
        // Descobre o nome do usuario logado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Descobre o tipo de usuario dos outros usuarios logados
        String cnpj = ((UserDetails) principal).getUsername();
        Perfil tipoDosOutrosUsuarios = tipoDosOutrosUsuarios(cnpj);

        // Cria a lista dos perfis desejados usando o tipo do usuario descoberto anteriormente
        List<Perfil> perfis = new ArrayList<>();
        Perfil perfil = new Perfil(tipoDosOutrosUsuarios.getId(), tipoDosOutrosUsuarios.getNome());
        perfis.add(perfil);

        return perfis;
    }

    public Perfil tipoDosOutrosUsuarios(String cnpj) {
            Optional<Usuario> optional = usuarioRepository.findBycnpj(cnpj);
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
