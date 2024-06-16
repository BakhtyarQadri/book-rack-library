package com.intigral.assignment.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component @NoArgsConstructor @Getter
@AllArgsConstructor
@Slf4j
public class ApiResponse {

    private Error error;

    public static <T> ResponseEntity success(String status, T data, HttpStatus httpStatus) {
        // log.info(message);
        Success<T> successResponse = new Success<>(status, data);
        return new ResponseEntity<>(successResponse, httpStatus);
    }

    public static ResponseEntity failure(String message, HttpStatus httpStatus) {
        // log.error(message);
        Error error = new Error(httpStatus.value(), message);
        ApiResponse errorResponse = new ApiResponse(error);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}
