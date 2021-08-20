package br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.handlers;

import br.com.fcamara.acheiaquiapi.config.validacao.ErroDeCadastroDto;
import br.com.fcamara.acheiaquiapi.controller.authentication.form.exception.TipoDeUsuarioInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeDeserializacaoHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public List<ErroDeCadastroDto> handle(HttpMessageNotReadableException exception) {
        List<ErroDeCadastroDto> dto = new ArrayList<>();
        ErroDeCadastroDto campoErrado = new ErroDeCadastroDto("json", "erro na deserialização da requisição");
        dto.add(campoErrado);
        return dto;
    }
}
