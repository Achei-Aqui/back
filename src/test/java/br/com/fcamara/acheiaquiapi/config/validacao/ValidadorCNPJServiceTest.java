package br.com.fcamara.acheiaquiapi.config.validacao;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorCNPJServiceTest {

    @Test
    public void validandoCpnjComNumerosIguaisDeveRetornarUmaException() {
        assertThrows(RuntimeException.class, () -> ValidadorCNPJService.validar("11.111.111/1111-11"));
    }

    @Test
    public void validandoCpnjComPrimeiroDigitoInvalidoDeveRetornarUmaException() {
        assertThrows(RuntimeException.class, () -> ValidadorCNPJService.validar("03.775.758/0001-80"));
    }

    @Test
    public void validandoCpnjComSegundoDigitoInvalidoDeveRetornarUmaException() {
        assertThrows(RuntimeException.class, () -> ValidadorCNPJService.validar("03.775.758/0001-91"));
    }

    @Test
    public void validandoCpnjValidoDeveNaoRetornarUmaException() {
        List<String> cnpjs = new ArrayList<>();
        cnpjs.add("49.703.483/0001-80");
        cnpjs.add("41.583.653/0001-28");
        cnpjs.add("03.775.758/0001-90");
        cnpjs.add("13.240.259/0001-51");
        assertDoesNotThrow(() -> {
            for (String cnpj :
                    cnpjs) {
                ValidadorCNPJService.validar(cnpj);
            }
        });
    }
}