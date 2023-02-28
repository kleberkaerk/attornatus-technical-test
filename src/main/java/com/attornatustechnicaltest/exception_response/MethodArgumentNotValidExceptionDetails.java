package com.attornatustechnicaltest.exception_response;

import java.util.Objects;

public class MethodArgumentNotValidExceptionDetails {

    private final String error;
    private final String message;

    private MethodArgumentNotValidExceptionDetails(String error, String message) {

        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodArgumentNotValidExceptionDetails that = (MethodArgumentNotValidExceptionDetails) o;
        return Objects.equals(error, that.error) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, message);
    }

    public static final class MethodArgumentNotValidExceptionDetailsBuilder {
        private String error;
        private String message;

        private MethodArgumentNotValidExceptionDetailsBuilder() {
        }

        public static MethodArgumentNotValidExceptionDetailsBuilder builder() {
            return new MethodArgumentNotValidExceptionDetailsBuilder();
        }

        public MethodArgumentNotValidExceptionDetailsBuilder error(String error) {
            this.error = error;
            return this;
        }

        public MethodArgumentNotValidExceptionDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MethodArgumentNotValidExceptionDetails build() {
            return new MethodArgumentNotValidExceptionDetails(error, message);
        }
    }
}
