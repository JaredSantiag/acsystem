package com.cja.acsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AcsystemApiRestApplication {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper ();
	}

	public static void main(String[] args) {
		SpringApplication.run(AcsystemApiRestApplication.class, args);
	}
	
}
