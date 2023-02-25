package com.attornatustechnicaltest.domain;

public class Person {

    private String name;
    private String dateOfBirth;

    private Person(String name, String dateOfBirth) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public static final class PersonBuilder {
        private String name;
        private String dateOfBirth;

        private PersonBuilder() {
        }

        public static PersonBuilder builder() {

            return new PersonBuilder();
        }

        public PersonBuilder name(String name) {

            this.name = name;
            return this;
        }

        public PersonBuilder dateOfBirth(String dateOfBirth) {

            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Person build() {

            return new Person(name, dateOfBirth);
        }
    }
}
