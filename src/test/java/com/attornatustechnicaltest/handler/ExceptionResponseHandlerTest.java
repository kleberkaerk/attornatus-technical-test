package com.attornatustechnicaltest.handler;

import com.attornatustechnicaltest.exception.NonExistentPersonException;
import com.attornatustechnicaltest.exception_response.MethodArgumentNotValidExceptionDetails;
import com.attornatustechnicaltest.exception_response.NonExistentPersonExceptionDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(SpringExtension.class)
class ExceptionResponseHandlerTest {

    @InjectMocks
    private ExceptionResponseHandler exceptionResponseHandler = new ExceptionResponseHandler();
    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private HttpHeaders httpHeaders;
    @Mock
    private WebRequest webRequest;

    private MethodArgumentNotValidExceptionDetails methodArgumentNotValidExceptionDetailsToComparisonInHandleMethodArgumentNotValid;
    private ResponseEntity<NonExistentPersonExceptionDetails> responseEntityToComparisonInHandleNonExistentPerson;

    void setMethodArgumentNotValidExceptionDetailsToComparisonInHandleMethodArgumentNotValid() {

        this.methodArgumentNotValidExceptionDetailsToComparisonInHandleMethodArgumentNotValid = MethodArgumentNotValidExceptionDetails
                .MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("Bad Request")
                .message("default message of error")
                .build();
    }

    void setResponseEntityToComparisonInHandleNonExistentPerson() {

        this.responseEntityToComparisonInHandleNonExistentPerson = new ResponseEntity<>(
                NonExistentPersonExceptionDetails.NonExistentPersonExceptionDetailsBuilder.builder()
                        .message("error message")
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @BeforeEach
    void initializeObjects() {

        this.setMethodArgumentNotValidExceptionDetailsToComparisonInHandleMethodArgumentNotValid();
        this.setResponseEntityToComparisonInHandleNonExistentPerson();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(this.methodArgumentNotValidException.getBody())
                .thenReturn(ProblemDetail.forStatus(400));

        BDDMockito.when(this.methodArgumentNotValidException.getFieldError())
                .thenReturn(new FieldError("object", "field", "default message of error"));
    }

    @Test
    void handleMethodArgumentNotValid_returnsAResponseEntityWithAMethodArgumentNotValidExceptionDetailsObjectAndAStatusCodeBadRequest_wheneverCalled() {

        ResponseEntity<Object> responseEntityHandleMethodArgumentNotValid = this.exceptionResponseHandler.handleMethodArgumentNotValid(
                this.methodArgumentNotValidException,
                this.httpHeaders,
                HttpStatusCode.valueOf(400),
                this.webRequest);

        Assertions.assertThat(responseEntityHandleMethodArgumentNotValid)
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(
                        this.methodArgumentNotValidExceptionDetailsToComparisonInHandleMethodArgumentNotValid,
                        HttpStatus.BAD_REQUEST));
    }

    @Test
    void handleNonExistentPerson_returnsAResponseEntityWithANonExistentPersonExceptionDetailsObjectAndAStatusCodeNotFound_wheneverCalled() {

        NonExistentPersonException nonExistentPersonException = new NonExistentPersonException("error message");

        Assertions.assertThatCode(() -> this.exceptionResponseHandler.handleNonExistentPerson(nonExistentPersonException))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.exceptionResponseHandler.handleNonExistentPerson(nonExistentPersonException))
                .isNotNull()
                .isEqualTo(this.responseEntityToComparisonInHandleNonExistentPerson);
    }
}