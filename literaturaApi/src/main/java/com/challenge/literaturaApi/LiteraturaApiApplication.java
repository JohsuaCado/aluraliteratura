package com.challenge.literaturaApi;

import com.challenge.literaturaApi.principal.Principal;
import com.challenge.literaturaApi.repository.AutorRepository;
import com.challenge.literaturaApi.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApiApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApiApplication.class, args);
	}
	@Override
	public void run(String... args)throws Exception {
		Principal principal = new Principal(repository,autorRepository);
		principal.menu();

	}
}
