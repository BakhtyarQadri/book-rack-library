package com.intigral.assignment.utils.validation;

import com.intigral.assignment.dto.BookRequestDto;
import org.apache.coyote.BadRequestException;

public class BookValidator {

    private BookValidator() {}

    public static void validateBookRequestDtoObj(BookRequestDto bookDto) throws Exception {
        if (bookDto.getName() == null || bookDto.getName().isEmpty()) {
            throw new BadRequestException("name shouldn't be missing / null / empty");
        } else if (bookDto.getName().matches("^\\d+$")) {
            throw new BadRequestException("name shouldn't only contain digits");
        } else if (bookDto.getDescription() == null) {
            throw new BadRequestException("description shouldn't be missing / null");
        } else if (bookDto.getDescription().matches("^\\d+$")) {
            throw new BadRequestException("description shouldn't only contain digits");
        } else if (bookDto.getRackId() == null) {
            throw new BadRequestException("rack id shouldn't be missing / null");
        } else if (bookDto.getRackId() < 1) {
            throw new BadRequestException("rack id shouldn't be less than 1");
        }
    }
}
