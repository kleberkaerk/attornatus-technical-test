package com.attornatustechnicaltest.dto.request;

import java.util.Objects;

public class PersonRequestPut {

    private final Long id;
    private final String name;
    private final String dateOfBirth;

    private PersonRequestPut(Long id, String name, String dateOfBirth) {
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
        PersonRequestPut that = (PersonRequestPut) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth);
    }

    public static final class PersonRequestPutBuilder {
        private Long id;
        private String name;
        private String dateOfBirth;

        private PersonRequestPutBuilder() {
        }

        public static PersonRequestPutBuilder builder() {
            return new PersonRequestPutBuilder();
        }

        public PersonRequestPutBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PersonRequestPutBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonRequestPutBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonRequestPut build() {
            return new PersonRequestPut(id, name, dateOfBirth);
        }
    }
}
