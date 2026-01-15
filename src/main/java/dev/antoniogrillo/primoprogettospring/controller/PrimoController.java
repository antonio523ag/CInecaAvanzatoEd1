package dev.antoniogrillo.primoprogettospring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class PrimoController {

    @GetMapping
    public String sayHello(){
        return "Hello World";
    }

    public ResponseEntity<Void> tornaErrore(){
        //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/provaURL/{parametro1}/{parametro2}")
    public ResponseEntity<String> urlParameter(@PathVariable String parametro1,@PathVariable("parametro2") long eta){
        return ResponseEntity.ok("Parametro 1: " + parametro1 + ", Parametro 2: " + eta);
    }

    @GetMapping("/param") //localhost:8080/hello/param?parametro1=test&parametro2=20
    public ResponseEntity<String> urlParameter2(@RequestParam String parametro1,@RequestParam("parametro2") long eta){
        return ResponseEntity.ok("Parametro 1: " + parametro1 + ", Parametro 2: " + eta);
    }


}
