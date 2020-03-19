package testgroup.crud_springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import testgroup.crud_springboot.model.User;

@SpringBootApplication
public class CrudSpringbootApplication {

	private static final Logger log = LoggerFactory.getLogger(CrudSpringbootApplication.class);


	public static void main(String[] args) {

		SpringApplication.run(CrudSpringbootApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
