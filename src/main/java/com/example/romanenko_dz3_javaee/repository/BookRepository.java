package com.example.romanenko_dz3_javaee.repository;

import com.example.romanenko_dz3_javaee.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE %:param% OR lower(b.isbn) LIKE %:param%")
    List<Book> getAllByIsbnOrName(@Param("param") String query);
}
