package com.intigral.assignment.controller;

import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.service.BookService;
import com.intigral.assignment.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    private ResponseEntity getBooks() {
        try {
            var response = bookService.getBooks();
            return ApiResponse.success("success", response, HttpStatus.OK);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("issue")
    private ResponseEntity getBooksIssue() {
        try {
            throw new Exception("error occured");
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // return List<Book>
    @GetMapping("{id}")
    private ResponseEntity getBookById(@PathVariable Integer id) {
        try {
            var response = bookService.getBookById(id);
            return ApiResponse.success("success", response, HttpStatus.OK);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    private ResponseEntity addBook(@RequestBody @Valid BookRequestDto bookDto) {
        try {
            String response = bookService.addBook(bookDto);
            return ApiResponse.success("success", response, HttpStatus.CREATED);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
