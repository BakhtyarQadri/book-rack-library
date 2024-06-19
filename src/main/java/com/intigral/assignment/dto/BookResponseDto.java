package com.intigral.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookResponseDto {

    Integer bookId;
    String bookName;
    String bookDescription;
    Integer rackId;
    String libraryName;
}
