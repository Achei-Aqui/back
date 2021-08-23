package br.com.fcamara.acheiaquiapi.controller;

import br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.CategoriaInexistenteException;
import br.com.fcamara.acheiaquiapi.controller.dto.ContatoDto;
import br.com.fcamara.acheiaquiapi.controller.form.AtualizarForm;
import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import br.com.fcamara.acheiaquiapi.repository.contato.ContatoRepository;
import br.com.fcamara.acheiaquiapi.repository.contato.EnderecoRepository;
import br.com.fcamara.acheiaquiapi.repository.PerfilRepository;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Page<ContatoDto> listaDeContatos(@PageableDefault(sort="cnpj", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

//      List<Contato> contatos = new ArrayList<>();

        List<Perfil> perfis = construirListaDePerfilUsandoUsuarioLogado();

        Page<Usuario> usuarios = usuarioRepository.findAllByPerfisIn(perfis, paginacao);

//        for ( Usuario usuario :
//             usuarios ) {
//            contatos.add(usuario.getContato());
//        }

        return ContatoDto.converter(usuarios);

    }

    @GetMapping("/{categoria}")
    public Page<ContatoDto> listaDeContatosFiltradaPorCategoria(@PageableDefault(sort="cnpj", direction = Sort.Direction.DESC, page = 0, size = 10)  Pageable paginacao, @PathVariable String categoria ) {
//        List<Contato> contatos = new ArrayList<>();

        try {
            Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase(Locale.ROOT));

            List<Perfil> perfis = construirListaDePerfilUsandoUsuarioLogado();

            Page<Usuario> usuariosPorCategoria = usuarioRepository.findAllByPerfisInAndContato_Categoria(perfis, categoriaEnum, paginacao);

//            for (Usuario usuario:
//                    usuariosPorCategoria) {
//                contatos.add(usuario.getContato());
//            }

            return ContatoDto.converter(usuariosPorCategoria);

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

    @PutMapping
    public ResponseEntity<?> atualizarSeuContato(@RequestBody AtualizarForm form) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Optional<Usuario> optional = usuarioRepository.findBycnpj(username);
        if(optional.isPresent()) {
            Usuario usuario = optional.get();
            Usuario usuarioAtualizado = form.atualizarUsuario(usuario);

            System.out.println(usuarioAtualizado.getEmail());
            usuarioRepository.save(usuarioAtualizado);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
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
