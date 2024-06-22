package com.intigral.assignment.controller;

import com.intigral.assignment.dto.BookResponseDto;
import com.intigral.assignment.service.BookService;
import com.intigral.assignment.utils.response.ApiResponse;
import com.intigral.assignment.utils.response.Error;
import com.intigral.assignment.utils.response.ErrorCode;
import com.intigral.assignment.utils.response.Success;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBooksApiBooksFound() {
        List<BookResponseDto> books = new ArrayList<>();
        books.add(new BookResponseDto(1, "Test Book 1", "Test Description", 1, "Test Library"));
        books.add(new BookResponseDto(2, "Test Book 2", "Test Description", 1, "Test Library"));

        when(bookService.getBooks()).thenReturn(books);

        ResponseEntity<Success> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().status());
        assertEquals(books, response.getBody().data());
    }

    @Test
    public void getAllBooksApiNoBookFound() {
        List<BookResponseDto> books = new ArrayList<>();

        when(bookService.getBooks()).thenReturn(books);

        ResponseEntity<Success> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().status());
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
}