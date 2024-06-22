package com.intigral.assignment.controller;

import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.dto.BookResponseDto;
import com.intigral.assignment.exception.BadRequestException;
import com.intigral.assignment.service.BookService;
import com.intigral.assignment.utils.response.ApiResponse;
import com.intigral.assignment.utils.response.Success;
import com.intigral.assignment.utils.validation.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity addBook(@RequestBody BookRequestDto bookRequestDto) {
        try {
            BookValidator.validateAddBookRequest(bookRequestDto);
            String response = bookService.addBook(bookRequestDto);
            return ApiResponse.success("success", response, HttpStatus.CREATED);
        } catch (BadRequestException exception) {
            return ApiResponse.failure(exception.getErrorCode(), exception.getErrorMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getBooks() {
        try {
            List<BookResponseDto> response = bookService.getBooks();
            return ApiResponse.success("success", response, HttpStatus.OK);
        } catch (Exception exception) {
            return ApiResponse.failure(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
