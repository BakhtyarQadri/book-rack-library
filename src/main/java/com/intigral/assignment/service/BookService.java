package com.intigral.assignment.service;

import com.intigral.assignment.dao.BookRepository;
import com.intigral.assignment.dao.RackRepository;
import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.dto.BookResponseDto;
import com.intigral.assignment.exception.BadRequestException;
import com.intigral.assignment.model.Book;
import com.intigral.assignment.model.Rack;
import com.intigral.assignment.utils.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final RackRepository rackRepository;

    public String addBook(BookRequestDto bookRequestDto) throws Exception {

        // rack id should exist
        Rack rack = rackRepository.findById(bookRequestDto.rackId()).orElseThrow(() -> new BadRequestException(ErrorCode.RACK_ID_NOT_EXIST, "rack id does not exist"));

        Book book = convertDtoToEntity(bookRequestDto, rack);
        bookRepository.save(book);
        return "Book added";
    }

    private Book convertDtoToEntity(BookRequestDto bookRequestDto, Rack rack) {
        Book bookObj = new Book();
        bookObj.setName(bookRequestDto.name());
        bookObj.setDescription(bookRequestDto.description());
        bookObj.setRack(rack);
        return bookObj;
    }

    public List<BookResponseDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        return convertEntityToDto(books);
    }

    private List<BookResponseDto> convertEntityToDto(List<Book> books) {
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for (Book book : books) {
            bookResponseDtos.add(
                new BookResponseDto(book.getId(), book.getName(), book.getDescription(), book.getRack().getId(), book.getRack().getLibrary().getName())
            );
        }
        return bookResponseDtos;
    }

}
