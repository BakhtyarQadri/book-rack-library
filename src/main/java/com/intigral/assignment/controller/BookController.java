package com.intigral.assignment.controller;

import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.service.BookService;
import com.intigral.assignment.utils.response.ApiResponse;
import com.intigral.assignment.utils.validation.BookValidator;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
    private ResponseEntity addBook(@RequestBody BookRequestDto bookDto) {
        try {
            BookValidator.validateBookRequestDtoObj(bookDto);
            String response = bookService.addBook(bookDto);
            return ApiResponse.success("success", response, HttpStatus.CREATED);
        } catch (BadRequestException exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
