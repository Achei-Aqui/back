package br.com.fcamara.acheiaquiapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AcheiaquiApiApplication {

	private static Logger logger = LoggerFactory.getLogger(AcheiaquiApiApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a api de contatos de empresas");
		SpringApplication.run(AcheiaquiApiApplication.class, args);
		logger.info("Api de contatos empresariais iniciada e pronta para receber requisições");
	}

}
