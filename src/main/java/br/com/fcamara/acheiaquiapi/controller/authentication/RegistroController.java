package br.com.fcamara.acheiaquiapi.controller.authentication;

import br.com.fcamara.acheiaquiapi.controller.authentication.form.CadastroForm;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.model.contato.Endereco;
import br.com.fcamara.acheiaquiapi.repository.ContatoRepository;
import br.com.fcamara.acheiaquiapi.repository.EnderecoRepository;
import br.com.fcamara.acheiaquiapi.repository.PerfilRepository;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegistroController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody @Valid CadastroForm form) {
        Optional<Usuario> optional = usuarioRepository.findBycnpj(form.getCnpj());
        if(optional.isEmpty()) {
            Endereco endereco = form.criarEndereco();
            Contato contato = form.criarContato(endereco);
            Usuario usuario = form.criarUsuario(contato, perfilRepository);

            enderecoRepository.save(endereco);
            contatoRepository.save(contato);
            usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        throw new UsuarioJaExistenteException();
    }
}
