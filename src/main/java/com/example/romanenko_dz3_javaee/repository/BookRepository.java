package com.example.romanenko_dz3_javaee.repository;

import com.example.romanenko_dz3_javaee.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Integer> {

    BookEntity findByIsbn(String isbn);

    @Query("SELECT b FROM BookEntity b WHERE lower(b.title) LIKE %:param% OR lower(b.isbn) LIKE %:param%")
    List<BookEntity> getAllByIsbnOrName(@Param("param") String param);
}
