package com.example.romanenko_dz3_javaee.controller;

import com.example.romanenko_dz3_javaee.dto.BookDto;
import com.example.romanenko_dz3_javaee.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping("/book-create")
    public String bookForm(){
        return "redirect:/filter";
    }


//    @GetMapping("show_books")
//    public String getBooks(Model model) {
//        model.addAttribute("books", bookService.getAllBooks());
//        return "book-create";
//    }

    @PostMapping("/book-create")
    public String createBook(@ModelAttribute BookDto book) {
        bookService.saveBook(book);
        return "redirect:/filter";
    }
//    @PostMapping(value = "/filter")
//    public List<BookDto> findBooks(@RequestBody final BookDto book) {
//        return bookService.findBooksByTitleAndIsbn(book.getTitle(), book.getIsbn());
//    }
    @RequestMapping(path = {"/","/filter"})
    public String findBooks(BookDto book ,Model model, @RequestParam(value ="isbnKey", required = false) String isbn, @RequestParam(value ="titleKey", required = false) String title) {
        List<BookDto> books;
        if(isbn!=null && title!=null){
            books = bookService.findBooksByTitleAndIsbn(title, isbn);
        }else{
            books = bookService.getAllBooks();
        }
        model.addAttribute("books", books);
        return "index";
    }
}
