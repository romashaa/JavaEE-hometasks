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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<BookDto>> saveBook(@RequestBody final BookDto book) {
        booksService.saveBook(book);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAllBooks());
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public ResponseEntity<List<BookDto>> findBooks(@RequestBody final BookDto book) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.searchBooks(book.getTitle(), book.getISBN()));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAllBooks());
    }
}
