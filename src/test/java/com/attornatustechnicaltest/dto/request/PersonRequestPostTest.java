package com.attornatustechnicaltest.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonRequestDTOTest {

    private PersonRequestDTO personRequestDTO;
    private PersonRequestDTO samePersonRequestDTO;
    private PersonRequestDTO differentPersonRequestDTO;

    void setPersonRequestDTO() {

        this.personRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();
    }
    void setSamePersonRequestDTO() {

        this.samePersonRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();
    }
    void setDifferentPersonRequestDTO() {

        this.differentPersonRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder()
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonRequestDTO();
        this.setSamePersonRequestDTO();
        this.setDifferentPersonRequestDTO();
    }

    @Test
    void getName() {

        Assertions.assertThat(this.personRequestDTO.getName())
                .isNotNull()
                .isEqualTo("name1");
    }

    @Test
    void getDateOfBirth() {

        Assertions.assertThat(this.personRequestDTO.getDateOfBirth())
                .isNotNull()
                .isEqualTo("01-01-2000");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.personRequestDTO)
                .isEqualTo(this.samePersonRequestDTO)
                .isNotEqualTo(this.differentPersonRequestDTO);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.personRequestDTO)
                .hasSameHashCodeAs(this.samePersonRequestDTO);
    }
}