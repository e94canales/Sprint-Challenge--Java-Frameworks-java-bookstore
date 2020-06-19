package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> getAllBooks(){
        List<Book> bookList = bookService.findAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/books", consumes = {"application/json"})
    public ResponseEntity<?> postBook(@Valid @RequestBody Book book){
        book.setBookid(0);
        book = bookService.save(book);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI bookURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{bookid}")
                .buildAndExpand(book.getBookid())
                .toUri();
        responseHeaders.setLocation(bookURI);

        return new ResponseEntity<>(book.getBooktitle() + " has been created", responseHeaders, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable long id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
