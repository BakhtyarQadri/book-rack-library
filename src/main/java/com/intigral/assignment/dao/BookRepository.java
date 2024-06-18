package com.intigral.assignment.dao;

import com.intigral.assignment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Optional<Book> findByName(String bookName);
    Book findByName(String bookName);
}
