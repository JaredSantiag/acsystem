package com.cja.acsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
<<<<<<< HEAD
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
=======
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4

@SpringBootApplication
public class AcsystemApiRestApplication {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper ();
	}

	public static void main(String[] args) {
		SpringApplication.run(AcsystemApiRestApplication.class, args);
	}
<<<<<<< HEAD
	
=======

>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
}
