package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/test")
public class TestController {
    
    @GetMapping("/")
    public ResponseEntity<Object> getNote(){//c
        return new ResponseEntity<>("Protected Resource:"
                + "Details of Note ",HttpStatus.OK );//c
    }
}
