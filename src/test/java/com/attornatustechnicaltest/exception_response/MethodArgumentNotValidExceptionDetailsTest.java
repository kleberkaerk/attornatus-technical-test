package com.attornatustechnicaltest.exception_response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodArgumentNotValidExceptionDetailsTest {

    private MethodArgumentNotValidExceptionDetails methodArgumentNotValidExceptionDetails;
    private MethodArgumentNotValidExceptionDetails sameMethodArgumentNotValidExceptionDetails;
    private MethodArgumentNotValidExceptionDetails differentMethodArgumentNotValidExceptionDetails;

    @BeforeEach
    void initializeObjects() {

        this.methodArgumentNotValidExceptionDetails = MethodArgumentNotValidExceptionDetails.
                MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("error1")
                .message("message1")
                .build();

        this.sameMethodArgumentNotValidExceptionDetails = MethodArgumentNotValidExceptionDetails.
                MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("error1")
                .message("message1")
                .build();

        this.differentMethodArgumentNotValidExceptionDetails = MethodArgumentNotValidExceptionDetails.
                MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("error2")
                .message("message2")
                .build();
    }

    @Test
    void getError() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails.getError())
                .isNotNull()
                .isEqualTo("error1");
    }

    @Test
    void getMessage() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails.getMessage())
                .isNotNull()
                .isEqualTo("message1");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails)
                .isEqualTo(this.sameMethodArgumentNotValidExceptionDetails)
                .isNotEqualTo(this.differentMethodArgumentNotValidExceptionDetails);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails)
                .hasSameHashCodeAs(this.sameMethodArgumentNotValidExceptionDetails);

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails.hashCode())
                .isNotEqualTo(this.differentMethodArgumentNotValidExceptionDetails.hashCode());

    }
}