package ru.stroki.test.exception;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.stroki.test.dto.ExceptionDto;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.validation.ValidationException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@NoArgsConstructor
public class ExceptionControllerAdvice {

    @Autowired
    private DtoMapper dtoMapper;


    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ExceptionDto> constraintException(ConstraintViolationException ex) {
        return ResponseEntity.status(418).body(dtoMapper.getExceptionDto(418, ex.getMessage()));
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ExceptionDto> validationException(ValidationException ex) {
        return ResponseEntity.status(418).body(dtoMapper.getExceptionDto(418, ex.getReason()));
    }
}
