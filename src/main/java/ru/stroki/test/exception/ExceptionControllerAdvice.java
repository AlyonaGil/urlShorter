package ru.stroki.test.exception;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.stroki.test.dto.ExceptionDto;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.validation.ValidationException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@NoArgsConstructor
@EnableWebMvc
@Slf4j
public class ExceptionControllerAdvice {

    @Autowired
    private DtoMapper dtoMapper;


    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ExceptionDto> constraintException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(418).body(dtoMapper.getExceptionDto(418, ex.getMessage()));
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ExceptionDto> validationException(ValidationException ex) {
        log.error(ex.getReason());
        return ResponseEntity.status(418).body(dtoMapper.getExceptionDto(418, ex.getReason()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleNotFound(NoHandlerFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(404).body(dtoMapper.getExceptionDto(404, ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> otherException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(500).body(dtoMapper.getExceptionDto(500, ex.getMessage()));
    }
}
