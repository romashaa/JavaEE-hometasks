package com.example.romanenko_dz3_javaee.service;

import com.example.romanenko_dz3_javaee.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    List<BookDto> books = new ArrayList<>();

    public void saveBook(BookDto book) {
        books.add(book);
    }

    public List<BookDto> getAllBooks() {
        return books;
    }

public  List<BookDto> getBooks(String name) {
    return books.stream()
            .filter(bookDto -> bookDto.getTitle().contains(name) || bookDto.getISBN().contains(name))
            .collect(Collectors.toList());
}
}
