package com.sakinr.patika.airportreservationsystem.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class CustomJwtException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}
