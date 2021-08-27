package br.com.fcamara.acheiaquiapi.repository.authentication;

import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;

import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.repository.authentication.UsuarioRepository;
import br.com.fcamara.acheiaquiapi.repository.contato.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static br.com.fcamara.acheiaquiapi.model.contato.Categoria.ALIMENTOS;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Test
    public void deveSalvarUmUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuarioRepository.save(usuario);
        Optional<Usuario> optional = usuarioRepository.findById(1L);
        assertTrue(optional.isPresent());
    }

    @Test
    public void deveDeletarUmUsuario() {
        Usuario usuariox = new Usuario();
        usuariox.setId(2L);
        usuarioRepository.save(usuariox);
        usuarioRepository.deleteById(2L);
    }

    @Test
    public void deveCarregarUmUsuarioAoBuscarPeloSeuCNPJ() {
        Usuario usuario = new Usuario();
        String cnpj = "03.775.758/0001-90";
        usuario.setCnpj(cnpj);

        usuarioRepository.save(usuario);

        Optional<Usuario> optional = usuarioRepository.findBycnpj(cnpj);
        assertTrue(optional.isPresent());
    }

    @Test
    public void deveCarregarUmUsuarioAoBuscarPeloSeuId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        usuarioRepository.save(usuario);

        Optional<Usuario> optional = usuarioRepository.findById(1L);
        assertTrue(optional.isPresent());
    }

    @Test
    public void deveCarregarUmUsuarioAoBuscarPelaSuaCategoria() {
        Usuario usuario = new Usuario();
        Contato contato = new Contato();
        usuario.setContato(contato);
        usuario.getContato().setCategoria(Categoria.ALIMENTOS);

        contatoRepository.save(contato);
        usuarioRepository.save(usuario);

        List<Usuario> usuarios = usuarioRepository.findAllByContato_Categoria(ALIMENTOS);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void deveNaoCarregarUmUsuarioInexistente() {
        String cnpj = "49.703.483/0001-80";

        Optional<Usuario> optionalPorCnpj = usuarioRepository.findBycnpj(cnpj);
        List<Usuario> usuarios = usuarioRepository.findAllByContato_Categoria(Categoria.PERFUMARIA);
        Optional<Usuario> optionalPorId = usuarioRepository.findById(1L);


        assertTrue(optionalPorCnpj.isEmpty());
        assertEquals(0, usuarios.size());
        assertTrue(optionalPorId.isPresent());
    }


}