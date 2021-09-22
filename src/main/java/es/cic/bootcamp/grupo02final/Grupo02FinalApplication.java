package es.cic.bootcamp.grupo02final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Grupo02FinalApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Grupo02FinalApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Grupo02FinalApplication.class, args);
	}

}
