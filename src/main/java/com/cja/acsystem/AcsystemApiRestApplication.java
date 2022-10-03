package com.cja.acsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
