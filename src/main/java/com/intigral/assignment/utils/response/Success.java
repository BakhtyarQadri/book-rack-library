package com.intigral.assignment.utils.response;

public record Success<T>(String status, T data) {
}
