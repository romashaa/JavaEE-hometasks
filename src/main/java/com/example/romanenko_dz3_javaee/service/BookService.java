package com.example.romanenko_dz3_javaee.service;

import com.example.romanenko_dz3_javaee.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    List<BookDto> books= new ArrayList<>();

    public void saveBook(BookDto book) {
        books.add(book);
    }

    public List<BookDto> getAllBooks() {
        return books;
    }

    public List<BookDto> findBooksByTitleAndIsbn(String title, String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().contains(isbn) || book.getTitle().contains(title))
                .collect(Collectors.toList());
    }
}
