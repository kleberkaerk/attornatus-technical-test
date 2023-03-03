package com.attornatustechnicaltest.exception_response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class NonExistentPersonExceptionDetails {

    @Schema(description = "Mensagem de erro", example = "Pessoa inexistente, por favor verifique a pessoa e tente novamente.")
    private final String message;

    private NonExistentPersonExceptionDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonExistentPersonExceptionDetails that = (NonExistentPersonExceptionDetails) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    public static final class NonExistentPersonExceptionDetailsBuilder {
        private String message;

        private NonExistentPersonExceptionDetailsBuilder() {
        }

        public static NonExistentPersonExceptionDetailsBuilder builder() {
            return new NonExistentPersonExceptionDetailsBuilder();
        }

        public NonExistentPersonExceptionDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public NonExistentPersonExceptionDetails build() {
            return new NonExistentPersonExceptionDetails(message);
        }
    }
}
