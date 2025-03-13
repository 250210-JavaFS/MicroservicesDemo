package com.revature.RecommendationAPI.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recs")
@CrossOrigin
public class RecommendationController {


    /* Ok.. so why bother using a completely different App for Book recommendations?
    In this case, I just wanted to show an MSA working. They could have coexisted... BUT:

    An MSA benefits us by allowing us to have multiple services that can be SCALED and DEVELOPED independently
    This allows us to have a more flexible, fault-tolerant, loosely-coupled, and higher-performance app

    A monolithic app (like we've been using) can be difficult to scale, and they're more prone to system-wide failure
    If one service fails in a MSA, the other services can still function (maybe with reduced functionality)

    MSAs are more complex to devlop and maintain, but they can be way more flexible, durable, and powerful.
    Look into MSA components like service discovery, load balancing, circuit breakers, DB syncing, and API gateways*/
    @GetMapping
    public ResponseEntity<String[]> getBookRecs(){

        String[] books = {"Rec1", "Rec2", "Rec3"};

        return ResponseEntity.ok(books);

    }

}
