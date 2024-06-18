package com.intigral.assignment.service;

import com.intigral.assignment.dao.BookRepository;
import com.intigral.assignment.dao.RackRepository;
import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.model.Book;
import com.intigral.assignment.model.Rack;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final RackRepository rackRepository;

    public Object getBooks() {
        List<Book> books = bookRepository.findAll();
        // books = new ArrayList<>();
        return books.isEmpty() ? "No book exist" : books;
    }

    public Object getBookById(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.isEmpty() ? "No book exist" : book;
    }

    public String addBook(BookRequestDto bookDto) throws Exception {

        // book name should be unique
        if (bookRepository.findByName(bookDto.getName()) != null) {
            throw new BadRequestException("book name already exist");
        }

        // rack id should exist
        Rack rack = rackRepository.findById(bookDto.getRackId()).orElseThrow(() -> new BadRequestException("rack id does not exist"));

        Book book = getBookEntityInstance(bookDto, rack);
        // bookRepository.save(book);
        return "Book added";
    }

    private Book getBookEntityInstance(BookRequestDto bookDto, Rack rack) {
        Book bookObj = new Book();
        bookObj.setName(bookDto.getName());
        bookObj.setDescription(bookDto.getDescription());
        bookObj.setRack(rack);
        return bookObj;
    }
}
