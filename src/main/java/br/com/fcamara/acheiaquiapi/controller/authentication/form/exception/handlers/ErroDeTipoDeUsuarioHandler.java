package br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.handlers;

import br.com.fcamara.acheiaquiapi.config.validacao.ErroDeCadastroDto;
import br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.TipoDeUsuarioInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeTipoDeUsuarioHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TipoDeUsuarioInexistenteException.class)
    public List<ErroDeCadastroDto> handle(TipoDeUsuarioInexistenteException exception) {
        List<ErroDeCadastroDto> dto = new ArrayList<>();
        ErroDeCadastroDto campoErrado = new ErroDeCadastroDto("tipo", "tipo de usuario invalido");
        dto.add(campoErrado);
        return dto;
    }
}
