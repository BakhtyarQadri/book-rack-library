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

    public String addBook(BookRequestDto bookDto) throws Exception {

        // rack id should exist
        Rack rack = rackRepository.findById(bookDto.getRackId()).orElseThrow(() -> new BadRequestException("rack id does not exist"));

        Book book = transformBookDtoToEntity(bookDto, rack);
        bookRepository.save(book);
        return "Book added";
    }

    public Object getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.isEmpty() ? "No book exist" : books;
    }

    private Book transformBookDtoToEntity(BookRequestDto bookDto, Rack rack) {
        Book bookObj = new Book();
        bookObj.setName(bookDto.getName());
        bookObj.setDescription(bookDto.getDescription());
        bookObj.setRack(rack);
        return bookObj;
    }

}
