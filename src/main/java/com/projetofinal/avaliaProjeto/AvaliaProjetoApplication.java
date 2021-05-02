package com.projetofinal.avaliaProjeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class AvaliaProjetoApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(AvaliaProjetoApplication.class, args);
	}

}
