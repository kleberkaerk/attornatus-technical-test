package com.attornatustechnicaltest.exception_response;

import java.util.Objects;

public class AddressNotFoundExceptionDetails {

    private final String message;

    private AddressNotFoundExceptionDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressNotFoundExceptionDetails that = (AddressNotFoundExceptionDetails) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    public static final class AddressNotFoundExceptionDetailsBuilder {
        private String message;

        private AddressNotFoundExceptionDetailsBuilder() {
        }

        public static AddressNotFoundExceptionDetailsBuilder builder() {
            return new AddressNotFoundExceptionDetailsBuilder();
        }

        public AddressNotFoundExceptionDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public AddressNotFoundExceptionDetails build() {
            return new AddressNotFoundExceptionDetails(message);
        }
    }
}
