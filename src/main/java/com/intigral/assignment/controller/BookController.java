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

    @PostMapping
    public ResponseEntity addBook(@RequestBody BookRequestDto bookRequestDto) {
        try {
            BookValidator.validateBookRequestDtoObj(bookRequestDto);
            String response = bookService.addBook(bookRequestDto);
            return ApiResponse.success("success", response, HttpStatus.CREATED);
        } catch (BadRequestException exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getBooks() {
        try {
            var response = bookService.getBooks();
            return ApiResponse.success("success", response, HttpStatus.OK);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
