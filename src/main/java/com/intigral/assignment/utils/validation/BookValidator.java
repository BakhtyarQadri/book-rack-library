package com.intigral.assignment.utils.validation;

import com.intigral.assignment.dto.BookRequestDto;
import com.intigral.assignment.exception.BadRequestException;
import com.intigral.assignment.utils.response.ErrorCode;

public class BookValidator {

    private BookValidator() {}

    public static void validateAddBookRequest(BookRequestDto bookDto) throws Exception {
        if (bookDto.name() == null || bookDto.name().isEmpty()) {
            throw new BadRequestException(ErrorCode.MISSING_NAME, "name shouldn't be missing / null / empty");
        } else if (!bookDto.name().matches("^(?=.*[a-zA-Z])[a-zA-Z0-9\\s]*$")) {
            throw new BadRequestException(ErrorCode.INVALID_NAME, "name should only contains alphanumeric character");
        } else if (bookDto.description() == null || bookDto.description().isEmpty()) {
            throw new BadRequestException(ErrorCode.MISSING_DESCRIPTION, "description shouldn't be missing / null / empty");
        } else if (!bookDto.description().matches("^(?=.*[a-zA-Z])[a-zA-Z0-9\\s]*$")) {
            throw new BadRequestException(ErrorCode.INVALID_DESCRIPTION, "description should only contains alphanumeric character");
        } else if (bookDto.rackId() == null) {
            throw new BadRequestException(ErrorCode.MISSING_RACK_ID, "rack id shouldn't be missing / null");
        } else if (bookDto.rackId() < 1) {
            throw new BadRequestException(ErrorCode.INVALID_RACK_ID, "rack id shouldn't be less than 1");
        }
    }
}
