package com.attornatustechnicaltest.dto.response;

import java.util.Objects;

public class PersonResponse {

    private final Long id;
    private final String name;
    private final String dateOfBirth;

    private PersonResponse(Long id, String name, String dateOfBirth) {

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
        PersonResponse that = (PersonResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth);
    }

    public static final class PersonResponseBuilder {
        private Long id;
        private String name;
        private String dateOfBirth;

        private PersonResponseBuilder() {
        }

        public static PersonResponseBuilder builder() {
            return new PersonResponseBuilder();
        }

        public PersonResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PersonResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonResponseBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonResponse build() {
            return new PersonResponse(id, name, dateOfBirth);
        }
    }
}
