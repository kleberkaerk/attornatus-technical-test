package com.attornatustechnicaltest.exception_response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodArgumentNotValidExceptionDetailsTest {

    private MethodArgumentNotValidExceptionDetails methodArgumentNotValidExceptionDetails;
    private MethodArgumentNotValidExceptionDetails sameMethodArgumentNotValidExceptionDetails;
    private MethodArgumentNotValidExceptionDetails differentMethodArgumentNotValidExceptionDetails;

    void setMethodArgumentNotValidExceptionDetails() {

        this.methodArgumentNotValidExceptionDetails = MethodArgumentNotValidExceptionDetails.
                MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("error1")
                .message("message1")
                .build();
    }

    void setSameMethodArgumentNotValidExceptionDetails() {

        this.sameMethodArgumentNotValidExceptionDetails = MethodArgumentNotValidExceptionDetails.
                MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("error1")
                .message("message1")
                .build();
    }

    void setDifferentMethodArgumentNotValidExceptionDetails() {

        this.differentMethodArgumentNotValidExceptionDetails = MethodArgumentNotValidExceptionDetails.
                MethodArgumentNotValidExceptionDetailsBuilder.builder()
                .error("error2")
                .message("message2")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setMethodArgumentNotValidExceptionDetails();
        this.setSameMethodArgumentNotValidExceptionDetails();
        this.setDifferentMethodArgumentNotValidExceptionDetails();
    }

    @Test
    void getError() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails.getError())
                .isEqualTo("error1");
    }

    @Test
    void getMessage() {

        Assertions.assertThat(this.methodArgumentNotValidExceptionDetails.getMessage())
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
    }
}