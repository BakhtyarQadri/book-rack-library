package com.intigral.assignment.utils.response;

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

    public static ResponseEntity failure(String errorMessage, HttpStatus httpStatus) {
        // log.error(errorMessage);
        Error error = new Error(ErrorCode.INTERNAL_SERVER_ERROR, errorMessage);
        ApiResponse errorResponse = new ApiResponse(error);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    public static ResponseEntity failure(ErrorCode errorCode, String errorMessage, HttpStatus httpStatus) {
        // log.error(errorMessage);
        Error error = new Error(errorCode, errorMessage);
        ApiResponse errorResponse = new ApiResponse(error);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}
