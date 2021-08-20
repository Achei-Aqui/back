package br.com.fcamara.acheiaquiapi.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeCNPJInvalidoHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CnpjInvalidoException.class)
    public List<ErroDeCadastroDto> handle(CnpjInvalidoException exception) {
        List<ErroDeCadastroDto> dto = new ArrayList<>();
        ErroDeCadastroDto campoErrado = new ErroDeCadastroDto("cnpj", "cnpj invalido");
        dto.add(campoErrado);
        return dto;
    }
}
