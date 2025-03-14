package com.revature.RecommendationAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Supplier;

@SpringBootApplication
public class RecommendationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationApiApplication.class, args);
	}


	/* Our entire functionality will live here now -
	But I'll leave the controllers just for reference

	Since Lambdas typically deploy APIs that have a single function, this is a typical pattern

	Get Book Recs is going to be a Supplier function
	Suppliers "Supply" data, and don't expect any data back*/

	@Bean
	public Supplier<List<String>> getBookRecs(){
		return () -> List.of("Rec1", "Rec2", "Rec3");
		//Yes, in a real app, this would probably just be a call to the service which calls a DAO
	}


}
