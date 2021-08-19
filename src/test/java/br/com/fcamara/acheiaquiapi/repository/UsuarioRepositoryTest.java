package br.com.fcamara.acheiaquiapi.repository;

import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void deveCarregarUmUsuarioAoBuscarPeloSeuCNPJ() {
        Usuario usuario = new Usuario();
        String cnpj = "03.775.758/0001-90";
        usuario.setCnpj(cnpj);

        repository.save(usuario);

        Optional<Usuario> optional = repository.findBycnpj(cnpj);
        assertTrue(optional.isPresent());
    }

    @Test
    public void deveNaoCarregarUmUsuarioInexistente() {
        String cnpj = "49.703.483/0001-80";

        Optional<Usuario> optional = repository.findBycnpj(cnpj);
        assertTrue(optional.isEmpty());
    }
}