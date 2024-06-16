package com.intigral.assignment.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BookRequestDto {

//    @NotBlank(message = "kindly provide book id")
//    Integer id;

    @NotBlank(message = "shouldn't be null / empty")
    String name;

    @NotNull(message = "shouldn't be null")
    String description;

    @Min(1)
    @Max(100)
    @NotNull(message = "shouldn't be null")
    Integer rackId;
}
