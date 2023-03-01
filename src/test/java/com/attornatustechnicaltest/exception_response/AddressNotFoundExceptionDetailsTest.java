package com.attornatustechnicaltest.exception_response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressNotFoundExceptionDetailsTest {

    private AddressNotFoundExceptionDetails addressNotFoundExceptionDetails;
    private AddressNotFoundExceptionDetails sameAddressNotFoundExceptionDetails;
    private AddressNotFoundExceptionDetails differentAddressNotFoundExceptionDetails;

    void setAddressNotFoundExceptionDetails() {

        this.addressNotFoundExceptionDetails = AddressNotFoundExceptionDetails.
                AddressNotFoundExceptionDetailsBuilder.builder()
                .message("message1")
                .build();
    }

    void setSameAddressNotFoundExceptionDetails() {

        this.sameAddressNotFoundExceptionDetails = AddressNotFoundExceptionDetails.
                AddressNotFoundExceptionDetailsBuilder.builder()
                .message("message1")
                .build();
    }

    void setDifferentAddressNotFoundExceptionDetails() {

        this.differentAddressNotFoundExceptionDetails = AddressNotFoundExceptionDetails.
                AddressNotFoundExceptionDetailsBuilder.builder()
                .message("message2")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setAddressNotFoundExceptionDetails();
        this.setSameAddressNotFoundExceptionDetails();
        this.setDifferentAddressNotFoundExceptionDetails();
    }

    @Test
    void getMessage() {

        Assertions.assertThat(this.addressNotFoundExceptionDetails.getMessage())
                .isEqualTo("message1");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.addressNotFoundExceptionDetails)
                .isEqualTo(this.sameAddressNotFoundExceptionDetails)
                .isNotEqualTo(this.differentAddressNotFoundExceptionDetails);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.addressNotFoundExceptionDetails)
                .hasSameHashCodeAs(this.sameAddressNotFoundExceptionDetails);
    }
}