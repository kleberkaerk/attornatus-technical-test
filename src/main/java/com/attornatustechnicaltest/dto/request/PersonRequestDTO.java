package com.attornatustechnicaltest.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class PersonRequestDTO {

    @NotEmpty()
    private final String name;

    @NotEmpty()
    private final String dateOfBirth;

    private PersonRequestDTO(String name, String dateOfBirth) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public static final class PersonRequestDTOBuilder {
        private @NotEmpty() String name;
        private @NotEmpty() String dateOfBirth;

        private PersonRequestDTOBuilder() {
        }

        public static PersonRequestDTOBuilder builder() {

            return new PersonRequestDTOBuilder();
        }

        public PersonRequestDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonRequestDTOBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonRequestDTO build() {

            return new PersonRequestDTO(name, dateOfBirth);
        }
    }
}
