package br.com.fcamara.acheiaquiapi.controller.authentication;

import br.com.fcamara.acheiaquiapi.config.validacao.ErroDeCadastroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeUsuarioJaExistenteHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsuarioJaExistenteException.class)
    public List<ErroDeCadastroDto> handle(UsuarioJaExistenteException exception) {
        List<ErroDeCadastroDto> dto = new ArrayList<>();
        ErroDeCadastroDto campoErrado = new ErroDeCadastroDto("registro", "usuario já existente");
        dto.add(campoErrado);
        return dto;
    }
}
