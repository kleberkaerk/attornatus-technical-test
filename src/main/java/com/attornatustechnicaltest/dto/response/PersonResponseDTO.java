package com.attornatustechnicaltest.dto.response;

import java.util.Objects;

public class PersonResponseDTO {

    private final Long id;
    private final String name;
    private final String dateOfBirth;

    private PersonResponseDTO(Long id, String name, String dateOfBirth) {

        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonResponseDTO that = (PersonResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth);
    }

    public static final class PersonResponseDTOBuilder {
        private Long id;
        private String name;
        private String dateOfBirth;

        private PersonResponseDTOBuilder() {
        }

        public static PersonResponseDTOBuilder builder() {
            return new PersonResponseDTOBuilder();
        }

        public PersonResponseDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PersonResponseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonResponseDTOBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonResponseDTO build() {
            return new PersonResponseDTO(id, name, dateOfBirth);
        }
    }
}
