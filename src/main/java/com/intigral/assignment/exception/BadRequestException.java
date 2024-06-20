package com.intigral.assignment.exception;

import com.intigral.assignment.utils.response.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component @NoArgsConstructor @AllArgsConstructor
@Getter
public class BadRequestException extends Exception {
    ErrorCode errorCode;
    String errorMessage;
}
