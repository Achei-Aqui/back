package br.com.fcamara.acheiaquiapi.controller.contato;

import br.com.fcamara.acheiaquiapi.controller.contato.dto.ContatoDto;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.repository.authentication.PerfilRepository;
import br.com.fcamara.acheiaquiapi.repository.authentication.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuariologado")
public class UsuarioLogadoController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<ContatoDto> dadosDoUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String cnpj = ((UserDetails) principal).getUsername();
        Optional<Usuario> optionalUsuario = usuarioRepository.findBycnpj(cnpj);

        if(optionalUsuario.isPresent()) {
            ContatoDto contatoUsuarioLogado = new ContatoDto(optionalUsuario.get());
            return new ResponseEntity<ContatoDto>(contatoUsuarioLogado, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
