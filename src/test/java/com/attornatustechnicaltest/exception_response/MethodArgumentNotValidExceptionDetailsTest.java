package com.attornatustechnicaltest.exception_response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodArgumentNotValidExceptionDetailsTest {

    private MethodArgumentNotValidExceptionDetails methodArgumentNotValidExceptionDetails;

    @BeforeEach
    void setMethodArgumentNotValidExceptionDetails() {

        this.methodArgumentNotValidExceptionDetails = MethodArgumentNotValidExceptionDetails.
                MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("error")
                .message("message")
                .build();
    }

    @Test
    void getError() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails.getError())
                .isNotNull()
                .isEqualTo("error");
    }

    @Test
    void getMessage() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails.getMessage())
                .isNotNull()
                .isEqualTo("message");
    }
}