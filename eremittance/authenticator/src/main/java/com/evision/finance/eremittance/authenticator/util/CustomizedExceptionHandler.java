package com.evision.finance.eremittance.authenticator.util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomizedExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<GeneralExceptionResponse> handleAllExceptions(Exception ex,
                                                                           WebRequest request) {
        GeneralExceptionResponse exception = new GeneralExceptionResponse(new Date(), ex.getMessage(),
                                                                          request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RemittanceAppException.class)
    protected ResponseEntity<GeneralExceptionResponse> handleUserNotFoundExceptions(Exception ex,
                                                                                    WebRequest request) {
        GeneralExceptionResponse exception = new GeneralExceptionResponse(new Date(), ex.getMessage(),
                                                                          request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<GeneralExceptionResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        GeneralExceptionResponse exception = new GeneralExceptionResponse(new Date(), "Validation Failed",
                                                                          ex.getBindingResult()
                                                                                  .getAllErrors()
                                                                                  .stream()
                                                                                  .map(ObjectError::toString)
                                                                                  .collect(Collectors.toList())
                                                                                  .toString());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}