package br.com.fcamara.acheiaquiapi.config.swagger;

import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import com.google.common.net.HttpHeaders;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket contatoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.fcamara.acheiaquiapi"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(Usuario.class)
                .securitySchemes(Arrays.asList(new ApiKey("Bearer", HttpHeaders.AUTHORIZATION, In.HEADER.name())));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api de Contatos v3.0")
                .description("Lista de compradores e fornecedores")
                .version("3.0.0")
                .contact(new Contact("William Jonathan", "linkedin.com/in/william-jonathan-036599208/", "williamjonathansb@gmail.com"))
                .build();
    }

}
