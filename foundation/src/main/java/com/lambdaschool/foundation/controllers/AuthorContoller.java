package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorContoller {

    @Autowired
    private AuthorService authorService;

    // http://localhost:2019/authors/authors
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> getAllAuthors(){
        List<Author> authors = authorService.findAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
