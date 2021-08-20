package br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.handlers;

import br.com.fcamara.acheiaquiapi.config.validacao.ErroDeCadastroDto;
import br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.CategoriaInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeCategoriaInexistenteHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoriaInexistenteException.class)
    public List<ErroDeCadastroDto> handle(CategoriaInexistenteException exception) {
        List<ErroDeCadastroDto> dto = new ArrayList<>();
        ErroDeCadastroDto campoErrado = new ErroDeCadastroDto("categoria", "categoria invalida");
        dto.add(campoErrado);
        return dto;
    }
}
