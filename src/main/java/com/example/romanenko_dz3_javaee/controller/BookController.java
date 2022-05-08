package com.example.romanenko_dz3_javaee.controller;

import com.example.romanenko_dz3_javaee.entity.BookEntity;
import com.example.romanenko_dz3_javaee.entity.UserEntity;
import com.example.romanenko_dz3_javaee.service.BookService;
import com.example.romanenko_dz3_javaee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    @Autowired
     BookService booksService;



    @PostMapping
    public ResponseEntity<List<BookEntity>> saveBook(@RequestBody final BookEntity book) {
        booksService.saveBook(book);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAllBooks());
    }

    @GetMapping("/book/{isbn}")
    public String showBook(@PathVariable("isbn") String isbn, Model model) {
        model.addAttribute("book", booksService.getBookByIsbn(isbn));
        return "books/book";
    }



    @GetMapping
    public ResponseEntity<List<BookEntity>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAllBooks());
    }

    @GetMapping("/get-books")
    public ResponseEntity<List<BookEntity>> getBooks(@RequestParam(name = "param", required = false) final String param) {
        System.out.println("Accept get book request: " + (param == null ? "No query" : param));
        List<BookEntity> response;
        if(param == null) {
            response = booksService.getAllBooks();
        } else {
            response = booksService.getBooks(param);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}
