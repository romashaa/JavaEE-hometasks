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

//    @GetMapping("/book-create")
//    public String bookForm(){
//        return "book-create";
//    }
//    @PostMapping("/book-create")
//    public String saveBook(final BookDto bookDto, final Model model){
//        System.out.println("save book: " + bookDto);
//        model.addAttribute("book",bookDto);
//        return "saved-book";
//    }



        List<BookDto> books = new ArrayList<>();

        @GetMapping("/book-create")
        public String bookForm(){
            return "book-create";
        }


        @GetMapping("show_books")
        public String getBooks(Model model) {
            model.addAttribute("books", books);
            return "saved-book";
        }

    @PostMapping("/book-create")
    public String createBook(@ModelAttribute BookDto book) {
        books.add(book);
        return "redirect:/show_books";
    }
}
