package br.com.fcamara.acheiaquiapi.controller.contato;

import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.repository.authentication.UsuarioRepository;
import br.com.fcamara.acheiaquiapi.repository.contato.ContatoRepository;
import br.com.fcamara.acheiaquiapi.repository.contato.EnderecoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContatosControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void deveDevolver200CasoEstejaAutentificado() throws Exception {
//
//        URI uri = new URI("/contatos");
//        mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get(uri))
//                .andExpect(MockMvcResultMatchers
//                        .status()
//                        .is2xxSuccessful());
//    }

    @Test
    public void deveDevolver403CasoNaoEstejaAutentificado() throws Exception {
        URI uri = new URI("/contatos");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isForbidden());
    }
}