//package br.com.fcamara.acheiaquiapi.config.security;
//
//import br.com.fcamara.acheiaquiapi.repository.authentication.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//@Profile("test")
//public class TestSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AutenticacaoService autenticacaoService;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//
//    //Configuração de Autorização
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").permitAll()
//                .anyRequest().permitAll()
//                .and().csrf().disable();
//    }
//    //Configuração de Autenticação
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//    }
//
//
//}
