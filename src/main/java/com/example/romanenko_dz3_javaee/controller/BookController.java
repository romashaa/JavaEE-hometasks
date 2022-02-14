package com.example.romanenko_dz3_javaee.controller;

import com.example.romanenko_dz3_javaee.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

    @GetMapping("/book-create")
    public String bookForm(){
        return "book-create";
    }
    @PostMapping("/book-create")
    public String saveBook(final BookDto bookDto, final Model model){
        System.out.println("save book: " + bookDto);
        model.addAttribute("book",bookDto);
        return "saved-book";
    }
}
