package com.intigral.assignment.controller;

import com.intigral.assignment.dao.BookRepository;
import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.dto.BookResponseDto;
import com.intigral.assignment.exception.BadRequestException;
import com.intigral.assignment.model.Book;
import com.intigral.assignment.service.BookService;
import com.intigral.assignment.utils.response.Error;
import com.intigral.assignment.utils.response.ErrorCode;
import com.intigral.assignment.utils.response.Success;
import com.intigral.assignment.utils.validation.BookValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BookControllerTest {

    @InjectMocks
    BookController bookController;
    @Mock
    BookService bookService;
    @Mock
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addBookApiEmptyName() {
        BookRequestDto book = new BookRequestDto("", "Book Description", 1);

        ResponseEntity<Error> response = bookController.addBook(book);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.MISSING_NAME, response.getBody().code());
        assertEquals("name shouldn't be missing / null / empty", response.getBody().message());
    }

    @Test
    public void addBookApiInvalidName() {
        BookRequestDto book = new BookRequestDto("1234567890", "Book Description", 1);

        ResponseEntity<Error> response = bookController.addBook(book);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.INVALID_NAME, response.getBody().code());
        assertEquals("name should only contains alphanumeric character", response.getBody().message());
    }

    @Test
    public void addBookApiINullRackId() {
        BookRequestDto book = new BookRequestDto("Book 1", "Book Description", null);

        ResponseEntity<Error> response = bookController.addBook(book);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.MISSING_RACK_ID, response.getBody().code());
        assertEquals("rack id shouldn't be missing / null", response.getBody().message());    }

    @Test
    public void addBookApiInvalidRackId() {
        BookRequestDto book = new BookRequestDto("Book 1", "Book Description", -1);

        ResponseEntity<Error> response = bookController.addBook(book);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.INVALID_RACK_ID, response.getBody().code());
        assertEquals("rack id shouldn't be less than 1", response.getBody().message());
    }

    @Test
    public void addBookApiINullDescription() {
        BookRequestDto book = new BookRequestDto("Book 1", null, 1);

        ResponseEntity<Error> response = bookController.addBook(book);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.MISSING_DESCRIPTION, response.getBody().code());
        assertEquals("description shouldn't be missing / null / empty", response.getBody().message());
    }

    @Test
    public void addBookApiInvalidDescription() {
        BookRequestDto book = new BookRequestDto("Book 1", "12345", 1);

        ResponseEntity<Error> response = bookController.addBook(book);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.INVALID_DESCRIPTION, response.getBody().code());
        assertEquals("description should only contains alphanumeric character", response.getBody().message());
    }

    @Test
    public void getAllBooksApiBooksFound() {
        List<BookResponseDto> books = new ArrayList<>();
        books.add(new BookResponseDto(1, "Test Book 1", "Test Description", 1, "Test Library"));
        books.add(new BookResponseDto(2, "Test Book 2", "Test Description", 1, "Test Library"));

        when(bookService.getBooks()).thenReturn(books);

        ResponseEntity<Success> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody().status());
        assertEquals(books, response.getBody().data());
    }

    @Test
    public void getAllBooksApiNoBookFound() {
        List<Book> books = new ArrayList<>();

        when(bookRepository.findAll()).thenReturn(books);

        ResponseEntity<Success> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody().status());
        assertEquals(books, response.getBody().data());
    }

    @Test
    public void getAllBooksApiInternalServerError() {
        when(bookService.getBooks()).thenThrow(new RuntimeException("error occured"));

        ResponseEntity<Error> response = bookController.getBooks();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorCode.INTERNAL_SERVER_ERROR , response.getBody().code());
        assertEquals("error occured" , response.getBody().message());
    }

    @Test
    public void addBookApiSuccess() throws Exception {
        BookRequestDto book = new BookRequestDto("Book 1", "Book Description", 1);

        when(bookService.addBook(book)).thenReturn("Book added");

        BookValidator.validateAddBookRequest(book);
        ResponseEntity<Success> response = bookController.addBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody().status());
        assertEquals("Book added", response.getBody().data());
    }

    @Test
    public void addBookApiRackIdNotExist() throws Exception {
        BookRequestDto book = new BookRequestDto("Book 1", "Book Description", 100);

        when(bookService.addBook(book)).thenThrow(new BadRequestException(ErrorCode.RACK_ID_NOT_EXIST, "rack id does not exist"));

        BookValidator.validateAddBookRequest(book);
        ResponseEntity<Error> response = bookController.addBook(book);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.RACK_ID_NOT_EXIST, response.getBody().code());
        assertEquals("rack id does not exist", response.getBody().message());
    }
}