package com.revature.BookAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//No need for @ComponentScan etc, since our Classes are at the package level as main
public class BookApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApiApplication.class, args);
	}

}
