package com.revature.BookAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    //Method that doesn't interact with any other API
    @GetMapping
    public ResponseEntity<String[]> getAllBooks() {

        String[] books = {"Flowers for Algernon", "Sherlock Holmes",
                "The Taming of the Shrew", "The Shining", "The Very Hungry Caterpillar"};

        return ResponseEntity.ok(books);

    }

    //Method that sends an HTTP requests to another API to get book recommendations
    @GetMapping("/recs")
    public ResponseEntity<String[]> getBookRecs(){

        //Instanting a RestTemplate so we can make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        //Using RestTemplate to send an HTTP Request to another API
        String[] recs = restTemplate.getForObject("http://localhost:8081/recs", String[].class);

        return ResponseEntity.ok(recs);

    }

}
