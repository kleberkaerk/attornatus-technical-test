package com.attornatustechnicaltest.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonRequestPutTest {

    private PersonRequestPut personRequestPut;
    private PersonRequestPut samePersonRequestPut;
    private PersonRequestPut differentPersonRequestPut;

    void setPersonRequestPut() {

        this.personRequestPut = PersonRequestPut.PersonRequestPutBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setSamePersonRequestPut() {

        this.samePersonRequestPut = PersonRequestPut.PersonRequestPutBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setDifferentPersonRequestPut() {

        this.differentPersonRequestPut = PersonRequestPut.PersonRequestPutBuilder.builder()
                .id(2L)
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonRequestPut();
        this.setSamePersonRequestPut();
        this.setDifferentPersonRequestPut();
    }

    @Test
    void getId() {

        Assertions.assertThat(this.personRequestPut.getId())
                .isEqualTo(1L);
    }

    @Test
    void getName() {

        Assertions.assertThat(this.personRequestPut.getName())
                .isEqualTo("name1");
    }

    @Test
    void getDateOfBirth() {

        Assertions.assertThat(this.personRequestPut.getDateOfBirth())
                .isEqualTo("01-01-2001");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.personRequestPut)
                .isEqualTo(this.samePersonRequestPut)
                .isNotEqualTo(this.differentPersonRequestPut);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.personRequestPut)
                .hasSameHashCodeAs(this.samePersonRequestPut);
    }
}