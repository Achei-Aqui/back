package br.com.fcamara.acheiaquiapi.repository.authentication;

import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PerfilRepositoryTest {

    @Autowired
    private PerfilRepository perfilRepository;

    @Test
    public void deveCarregarUmPerfil() {
        Perfil comprador = new Perfil();
        comprador.setId(1L);
        comprador.setNome("ROLE_COMPRADOR");

        Perfil fornecedor = new Perfil();
        fornecedor.setId(2L);
        fornecedor.setNome("ROLE_FORNECEDOR");


        perfilRepository.save(comprador);
        Optional<Perfil> optionalComprador = perfilRepository.findByNome("ROLE_COMPRADOR");

        perfilRepository.save(fornecedor);
        Optional<Perfil> optionalFornecedor = perfilRepository.findByNome("ROLE_FORNECEDOR");

        assertTrue(optionalFornecedor.isPresent());
        assertTrue(optionalComprador.isPresent());
    }


}