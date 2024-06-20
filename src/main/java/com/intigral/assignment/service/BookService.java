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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final RackRepository rackRepository;

    public String addBook(BookRequestDto bookRequestDto) throws Exception {

        // rack id should exist
        Rack rack = rackRepository.findById(bookRequestDto.getRackId()).orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_RACK_ID, "rack id does not exist"));

        Book book = convertDtoToEntity(bookRequestDto, rack);
        bookRepository.save(book);
        return "Book added";
    }

    public Object getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.isEmpty() ? "No book exist" : convertEntityToDto(books);
    }

    private Book convertDtoToEntity(BookRequestDto bookRequestDto, Rack rack) {
        Book bookObj = new Book();
        bookObj.setName(bookRequestDto.getName());
        bookObj.setDescription(bookRequestDto.getDescription());
        bookObj.setRack(rack);
        return bookObj;
    }

    private List<BookResponseDto> convertEntityToDto(List<Book> books) {
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for (Book book : books) {
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setBookId(book.getId());
            bookResponseDto.setBookName(book.getName());
            bookResponseDto.setBookDescription(book.getDescription());
            bookResponseDto.setRackId(book.getRack().getId());
            bookResponseDto.setLibraryName(book.getRack().getLibrary().getName());
            bookResponseDtos.add(bookResponseDto);
        }
        return bookResponseDtos;
    }

}
