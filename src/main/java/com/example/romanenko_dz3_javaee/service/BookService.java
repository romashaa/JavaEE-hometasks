package com.example.romanenko_dz3_javaee.service;

import com.example.romanenko_dz3_javaee.entity.BookEntity;
import com.example.romanenko_dz3_javaee.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final EntityManager entityManager;

    private final BookRepository bookRepository;
//    @Transactional
//    public Book createBook(String title, String isbn, String author) {
//        Book book = new Book();
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        book.setAuthor(author);
//        return entityManager.merge(book);
//    }



    @Transactional
    public void saveBook(BookEntity book) {
        bookRepository.save(book);
    }
    @Transactional
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public  List<BookEntity> getBooks(String param) {
        return bookRepository.getAllByIsbnOrName(param);
    }

    @Transactional
    public BookEntity getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }
}
