package ru.stroki.test.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException {

    public ValidationException(String message) {
        super(HttpStatus.I_AM_A_TEAPOT, message);
    }
}
