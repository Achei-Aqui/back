package br.com.fcamara.acheiaquiapi.controller.authentication;

import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.repository.authentication.PerfilRepository;
import br.com.fcamara.acheiaquiapi.repository.authentication.UsuarioRepository;
import br.com.fcamara.acheiaquiapi.repository.contato.ContatoRepository;
import br.com.fcamara.acheiaquiapi.repository.contato.EnderecoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegistroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveDevolver201CasoOUsuarioSejaRegistrado() throws Exception {
        Perfil perfil = new Perfil();
        perfil.setId(1L);
        perfil.setNome("ROLE_COMPRADOR");
        perfilRepository.save(perfil);

        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"00.589.851/0001-11\", \"tipo\":\"ROLE_COMPRADOR\", " +
                "\"email\":\"teste@gmail.com\", " +
                "\"senha\":\"p^nt@m2qetR0\", " +
                "\"categoria\":\"ALIMENTOS\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 9999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is2xxSuccessful());
    }

    @Test
    public void deveDevolver400CasoOUsuarioJaExistir() throws Exception {
        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"00.589.851/0001-11\", \"tipo\":\"ROLE_COMPRADOR\", " +
                "\"email\":\"teste@gmail.com\", " +
                "\"senha\":\"p^nt@m2qetR0\", " +
                "\"categoria\":\"ALIMENTOS\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 9999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    public void deveDevolver400CasoOCpnjEstejaInvalido() throws Exception {
        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"00.589.851/0001-10\", \"tipo\":\"ROLE_COMPRADOR\", " +
                "\"email\":\"teste@gmail.com\", " +
                "\"senha\":\"p^nt@m2qetR0\", " +
                "\"categoria\":\"ALIMENTOS\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 9999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    public void deveDevolver400CasoOTipoEstejaInvalido() throws Exception {
        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"32.110.204/0001-71\", \"tipo\":\"ROLE_CMPRADOR\", " +
                "\"email\":\"teste@gmail.com\", " +
                "\"senha\":\"p^nt@m2qetR0\", " +
                "\"categoria\":\"ALIMENTOS\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 9999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    public void deveDevolver400CasoOEmailEstejaInvalido() throws Exception {
        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"32.110.204/0001-71\", \"tipo\":\"ROLE_COMPRADOR\", " +
                "\"email\":\"testegmail.com\", " +
                "\"senha\":\"p^nt@m2qetR0\", " +
                "\"categoria\":\"ALIMENTOS\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 9999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    public void deveDevolver400CasoASenhaNaoEstejaAdequada() throws Exception {
        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"32.110.204/0001-71\", \"tipo\":\"ROLE_COMPRADOR\", " +
                "\"email\":\"teste@gmail.com\", " +
                "\"senha\":\"123456\", " +
                "\"categoria\":\"ALIMENTOS\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 9999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    public void deveDevolver400CasoACategoriaEstejaInvalido() throws Exception {
        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"32.110.204/0001-71\", \"tipo\":\"ROLE_COMPRADOR\", " +
                "\"email\":\"teste@gmail.com\", " +
                "\"senha\":\"p^nt@m2qetR0\", " +
                "\"categoria\":\"categoriaInvalida\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 9999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    public void deveDevolver400CasoOTelefoneEstejaInvalido() throws Exception {
        URI uri = new URI("/register");
        String json = "{ \"cnpj\":\"32.110.204/0001-71\", \"tipo\":\"ROLE_COMPRADOR\", " +
                "\"email\":\"teste@gmail.com\", " +
                "\"senha\":\"p^nt@m2qetR0\", " +
                "\"categoria\":\"ALIMENTOS\", \"complemento\": " +
                "\"complementeDeTeste\", \"nome\":\"nomeDeTeste\"," +
                " \"telefone\":\"11 999-9999\", " +
                "\"cep\":\"01324001\", " +
                "\"bairro\":\"bairroDeTeste\", " +
                "\"cidade\":\"cidadeDeTeste\", " +
                "\"estado\":\"estadoDeTeste\", " +
                "\"rua\":\"ruaDeTeste\", " +
                "\"numero\":\"123\" }";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

}