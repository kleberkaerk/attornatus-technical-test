package com.attornatustechnicaltest.dto.response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonResponseTest {

    private PersonResponse personResponse;
    private PersonResponse samePersonResponse;
    private PersonResponse differentPersonResponse;

    void setPersonResponse() {

        this.personResponse = PersonResponse.PersonResponseBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setSamePersonResponse() {

        this.samePersonResponse = PersonResponse.PersonResponseBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setDifferentPersonResponse() {

        this.differentPersonResponse = PersonResponse.PersonResponseBuilder.builder()
                .id(2L)
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonResponse();
        this.setSamePersonResponse();
        this.setDifferentPersonResponse();
    }

    @Test
    void getId() {

        Assertions.assertThat(this.personResponse.getId())
                .isNotNull()
                .isEqualTo(1L);
    }

    @Test
    void getName() {

        Assertions.assertThat(this.personResponse.getName())
                .isNotNull()
                .isEqualTo("name1");
    }

    @Test
    void getDateOfBirth() {

        Assertions.assertThat(this.personResponse.getDateOfBirth())
                .isNotNull()
                .isEqualTo("01-01-2001");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.personResponse)
                .isEqualTo(this.samePersonResponse)
                .isNotEqualTo(this.differentPersonResponse);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.personResponse)
                .hasSameHashCodeAs(this.samePersonResponse);
    }
}