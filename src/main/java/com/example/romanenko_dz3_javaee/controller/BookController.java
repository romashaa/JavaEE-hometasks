package com.example.romanenko_dz3_javaee.controller;

import com.example.romanenko_dz3_javaee.dto.BookDto;
import com.example.romanenko_dz3_javaee.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService booksService;

    @PostMapping
    public ResponseEntity<List<BookDto>> saveBook(@RequestBody final BookDto book) {
        booksService.saveBook(book);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAllBooks());
    }

//    @PostMapping("/filter")
//    public ResponseEntity<List<BookDto>> findBooks(@RequestBody final BookDto book) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(booksService.searchBooks(book.getTitle(), book.getISBN()));
//    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAllBooks());
    }

    @GetMapping("/get-books")
    public ResponseEntity<List<BookDto>> getBooks(@RequestParam(name = "param", required = false) final String param) {
        System.out.println("Accept get book request: " + (param == null ? "No query" : param));
        List<BookDto> response;
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
