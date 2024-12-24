package com.challenger.literAlura;

import com.challenger.literAlura.main.Principal;
import com.challenger.literAlura.repositories.AuthorRepository;
import com.challenger.literAlura.repositories.BookRepository;
import com.challenger.literAlura.services.AuthorService;
import com.challenger.literAlura.services.BookService;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal main = new Principal(bookService, authorService);
		main.muestraElMenu();
	}
}
