package com.revature.BookAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController //Combination of @Controller and @ResponseBody
@RequestMapping("/books")
@CrossOrigin
public class BookController {

//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public BookController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    //We need this object to create circuit breakers in our methods
    @Autowired // field injection - BAD!
    private CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    //Method that doesn't interact with any other API
    @GetMapping
    public ResponseEntity<String[]> getAllBooks() {

        String[] books = {"Flowers for Algernon", "Sherlock Holmes",
                "The Taming of the Shrew", "The Shining", "The Very Hungry Caterpillar"};

        return ResponseEntity.ok(books);

    }

    //Method that sends an HTTP requests to another API to get book recommendations

    /*This method utilizes a CIRCUIT BREAKER

    Circuit breakers are good for error handling but it goes deeper than that
     Let's say RecApi is down. This failure cascades to the Book API if left unandled
     Circuit Breaker let us define fallback functionality and they prevent access to the downed service
     check application.properties for config*/
    @GetMapping("/recs")
    public ResponseEntity<String[]> getBookRecs(){

        //Create a circuit breaker and define a fallback method
        return circuitBreakerFactory.create("recsAPI").run(
                () -> {
                    //Instanting a RestTemplate so we can make HTTP requests
                    RestTemplate restTemplate = new RestTemplate();

                    //Using RestTemplate to send an HTTP Request to another API
                    String[] recs = restTemplate.getForObject("http://localhost:8081/recs", String[].class);

                    return ResponseEntity.ok(recs);
                },
                this::getRecsFallback //Method reference syntax - the Throwable will be included implicitly
        );
    }

    //Fallbacks-------------------------------------

    //Fallback method for getBookRecs
    public ResponseEntity<String[]> getRecsFallback(Throwable t){
        System.out.println("Fallback triggered due to: " + t.getMessage());
        return ResponseEntity.ok(
                new String[] {"Couldn't get recs! Here's a generic one:", "The Bible"});
        //TODO: better not send this as an array, maybe a DTO with an optional error message field
    }

}
