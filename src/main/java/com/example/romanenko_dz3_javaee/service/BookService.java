package com.example.romanenko_dz3_javaee.service;

import com.example.romanenko_dz3_javaee.entity.Book;
import com.example.romanenko_dz3_javaee.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public  List<Book> getBooks(String param) {
        return bookRepository.getAllByIsbnOrName(param);
    }

    @Transactional
    public Book getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }
}
