package com.example.romanenko_dz3_javaee.controller;

import com.example.romanenko_dz3_javaee.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/books")
public class BookController {


    List<BookDto> books = new ArrayList<>();

    @GetMapping("/book-create")
    public String bookForm(){
        return "book-create";
    }


    @GetMapping("show_books")
    public String getBooks(Model model) {
        model.addAttribute("books", books);
        return "book-create";
    }

    @PostMapping("/book-create")
    public String createBook(@ModelAttribute BookDto book) {
        books.add(book);
        return "redirect:/show_books";
    }
}
