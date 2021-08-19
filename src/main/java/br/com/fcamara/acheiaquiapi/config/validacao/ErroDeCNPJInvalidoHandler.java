package br.com.fcamara.acheiaquiapi.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeCNPJInvalidoHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CnpjInvalidoException.class)
    public String handle(CnpjInvalidoException exception) {
        return "CNPJ_INVALIDO";
    }
}
