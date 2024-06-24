package com.intigral.assignment.utils.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    private static final String SUCCESS_STATUS = "SUCCESS";

    private ApiResponse() {}

    public static <T> ResponseEntity<Success<T>> success(T data, HttpStatus httpStatus) {
        Success<T> success = new Success<>(SUCCESS_STATUS, data);
        return new ResponseEntity<>(success, httpStatus);
    }

    public static ResponseEntity<Error> failure(String errorMessage, HttpStatus httpStatus) {
        Error error = new Error(ErrorCode.INTERNAL_SERVER_ERROR, errorMessage);
        return new ResponseEntity<>(error, httpStatus);
    }

    public static ResponseEntity<Error> failure(ErrorCode errorCode, String errorMessage, HttpStatus httpStatus) {
        Error error = new Error(errorCode, errorMessage);
        return new ResponseEntity<>(error, httpStatus);
    }

}
