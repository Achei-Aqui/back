package br.com.fcamara.acheiaquiapi.config.security;

import br.com.fcamara.acheiaquiapi.model.Usuario;
import br.com.fcamara.acheiaquiapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String cnpj) throws UsernameNotFoundException {
        Optional<Usuario> optional = repository.findBycnpj(cnpj);

        if(optional.isPresent()) {
            return optional.get();
        }
        //Criar uma exception
        throw new RuntimeException();
    }
}
