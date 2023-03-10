package com.attornatustechnicaltest.handler;

import com.attornatustechnicaltest.exception.AddressNotFoundException;
import com.attornatustechnicaltest.exception.NonExistentPersonException;
import com.attornatustechnicaltest.exception_response.AddressNotFoundExceptionDetails;
import com.attornatustechnicaltest.exception_response.MethodArgumentNotValidExceptionDetails;
import com.attornatustechnicaltest.exception_response.NonExistentPersonExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return new ResponseEntity<>(
                MethodArgumentNotValidExceptionDetails.MethodArgumentNotValidExceptionDetailsBuilder.builder()
                        .error(ex.getBody().getTitle())
                        .message(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonExistentPersonException.class)
    public ResponseEntity<NonExistentPersonExceptionDetails> handleNonExistentPerson(NonExistentPersonException ex) {

        return new ResponseEntity<>(
                NonExistentPersonExceptionDetails.NonExistentPersonExceptionDetailsBuilder.builder()
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<AddressNotFoundExceptionDetails> handleAddressNotFound(AddressNotFoundException ex) {

        return new ResponseEntity<>(
                AddressNotFoundExceptionDetails.AddressNotFoundExceptionDetailsBuilder.builder()
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
