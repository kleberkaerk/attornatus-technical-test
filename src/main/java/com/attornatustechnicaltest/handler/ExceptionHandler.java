package com.attornatustechnicaltest.handler;

import com.attornatustechnicaltest.exception_response.MethodArgumentNotValidExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return new ResponseEntity<>(
                MethodArgumentNotValidExceptionDetails.MethodArgumentNotValidExceptionDetailsBuilder.builder()
                        .error(ex.getBody().getTitle())
                        .message("Campo " +
                                Objects.requireNonNull(ex.getFieldError()).getField() +
                                " inválido, verifique o valor e tente novamente.")
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
