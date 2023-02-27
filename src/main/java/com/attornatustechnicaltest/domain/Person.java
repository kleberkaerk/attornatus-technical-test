package com.attornatustechnicaltest.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    protected Person() {
    }

    private Person(String name, String dateOfBirth) {

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
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth);
    }

    public static final class PersonBuilder {
        private Long id;
        private String name;
        private String dateOfBirth;

        private PersonBuilder() {
        }

        public static PersonBuilder builder() {
            return new PersonBuilder();
        }

        public PersonBuilder id(Long id) {
            this.id = id;
            return this;
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
            Person person = new Person(name, dateOfBirth);
            person.id = this.id;
            return person;
        }
    }
}
