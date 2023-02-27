package com.attornatustechnicaltest.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

    private Person person;
    private Person samePerson;
    private Person differentPerson;

    @BeforeEach
    void initializeObjects() {

        this.person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();

        this.samePerson = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();

        this.differentPerson = Person.PersonBuilder.builder()
                .id(2L)
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
    }

    @Test
    void getId() {

        Assertions.assertThat(this.person.getId())
                .isNotNull()
                .isEqualTo(1L);
    }

    @Test
    void getName() {

        Assertions.assertThat(this.person.getName())
                .isNotNull()
                .isEqualTo("name1");
    }

    @Test
    void getDateOfBirth() {

        Assertions.assertThat(this.person.getDateOfBirth())
                .isNotNull()
                .isEqualTo("01-01-2000");
    }

    @Test
    void testToString() {

        Assertions.assertThat(this.person)
                .hasToString("Person{" +
                        "id=" + this.person.getId() +
                        ", name='" + this.person.getName() + '\'' +
                        ", dateOfBirth='" + this.person.getDateOfBirth() + '\'' +
                        '}');
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.person)
                .isEqualTo(this.samePerson);

        Assertions.assertThat(this.person.equals(this.differentPerson))
                .isFalse();
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.person)
                .hasSameHashCodeAs(this.samePerson);

        Assertions.assertThat(this.person.hashCode())
                .isNotEqualTo(this.differentPerson.hashCode());
    }
}