package br.com.fcamara.acheiaquiapi.controller.authentication;

import br.com.fcamara.acheiaquiapi.config.security.TokenService;
import br.com.fcamara.acheiaquiapi.controller.authentication.dto.TokenDto;
import br.com.fcamara.acheiaquiapi.controller.authentication.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @CrossOrigin(origins = "http://localhost:5050")
    @PostMapping
        public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);

            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
