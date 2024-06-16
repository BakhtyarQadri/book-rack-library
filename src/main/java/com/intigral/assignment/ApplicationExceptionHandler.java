package com.intigral.assignment;

import com.intigral.assignment.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ApplicationExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiResponse> handleInvalidArgumentException(MethodArgumentNotValidException exception) {
//        HashMap<String, String> errorHashMap = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(err -> {
//            errorHashMap.put(err.getField(), err.getDefaultMessage());
//        });
//        return ApiResponse.failure("FAILED", "", errorHashMap, HttpStatus.BAD_REQUEST, exception);
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public HashMap<String, String> handleInvalidArgumentException(MethodArgumentNotValidException ex) {
//        HashMap<String, String> errorHashMap = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(err -> {
//            errorHashMap.put(err.getField(), err.getDefaultMessage());
//        });
//        return errorHashMap;
//    }
}
