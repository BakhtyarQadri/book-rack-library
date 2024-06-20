package com.intigral.assignment.utils.validation;

import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.exception.BadRequestException;
import com.intigral.assignment.utils.response.ErrorCode;

public class BookValidator {

    private BookValidator() {}

    public static void validateBookRequestDtoObj(BookRequestDto bookDto) throws Exception {
        if (bookDto.getName() == null || bookDto.getName().isEmpty()) {
            throw new BadRequestException(ErrorCode.MISSING_NAME, "name shouldn't be missing / null / empty");
        } else if (!bookDto.getName().matches("^(?=.*[a-zA-Z])[a-zA-Z0-9\\s]*$")) {
            throw new BadRequestException(ErrorCode.INVALID_NAME, "name should only contains alphanumeric character");
        } else if (bookDto.getDescription() == null || bookDto.getDescription().isEmpty()) {
            throw new BadRequestException(ErrorCode.MISSING_DESCRIPTION, "description shouldn't be missing / null / empty");
        } else if (!bookDto.getDescription().matches("^(?=.*[a-zA-Z])[a-zA-Z0-9\\s]*$")) {
            throw new BadRequestException(ErrorCode.INVALID_DESCRIPTION, "description should only contains alphanumeric character");
        } else if (bookDto.getRackId() == null) {
            throw new BadRequestException(ErrorCode.MISSING_RACK_ID, "rack id shouldn't be missing / null");
        } else if (bookDto.getRackId() < 1) {
            throw new BadRequestException(ErrorCode.INVALID_RACK_ID, "rack id shouldn't be less than 1");
        }
    }
}
