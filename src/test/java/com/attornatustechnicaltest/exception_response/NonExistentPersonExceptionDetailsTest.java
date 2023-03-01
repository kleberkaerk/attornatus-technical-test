package com.attornatustechnicaltest.exception_response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NonExistentPersonExceptionDetailsTest {

    private NonExistentPersonExceptionDetails nonExistentPersonExceptionDetails;
    private NonExistentPersonExceptionDetails sameNonExistentPersonExceptionDetails;
    private NonExistentPersonExceptionDetails differentNonExistentPersonExceptionDetails;

    void setNonExistentPersonExceptionDetails() {

        this.nonExistentPersonExceptionDetails =
                NonExistentPersonExceptionDetails.NonExistentPersonExceptionDetailsBuilder.builder()
                        .message("message1")
                        .build();
    }

    void setSameNonExistentPersonExceptionDetails() {

        this.sameNonExistentPersonExceptionDetails =
                NonExistentPersonExceptionDetails.NonExistentPersonExceptionDetailsBuilder.builder()
                        .message("message1")
                        .build();
    }

    void setDifferentNonExistentPersonExceptionDetails() {

        this.differentNonExistentPersonExceptionDetails =
                NonExistentPersonExceptionDetails.NonExistentPersonExceptionDetailsBuilder.builder()
                        .message("message2")
                        .build();
    }


    @BeforeEach
    void initializeObjects() {

        this.setNonExistentPersonExceptionDetails();
        this.setSameNonExistentPersonExceptionDetails();
        this.setDifferentNonExistentPersonExceptionDetails();
    }

    @Test
    void getMessage() {

        Assertions.assertThat(this.nonExistentPersonExceptionDetails.getMessage())
                .isEqualTo("message1");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.nonExistentPersonExceptionDetails)
                .isEqualTo(this.sameNonExistentPersonExceptionDetails)
                .isNotEqualTo(this.differentNonExistentPersonExceptionDetails);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.nonExistentPersonExceptionDetails)
                .hasSameHashCodeAs(this.sameNonExistentPersonExceptionDetails);
    }
}