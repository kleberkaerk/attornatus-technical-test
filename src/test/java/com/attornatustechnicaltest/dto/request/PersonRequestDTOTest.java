package com.attornatustechnicaltest.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonRequestDTOTest {

    private PersonRequestDTO personRequestDTO;
    private PersonRequestDTO samePersonRequestDTO;
    private PersonRequestDTO differentPersonRequestDTO;

    @BeforeEach
    void initializeObjects() {

        this.personRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();

        this.samePersonRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();

        this.differentPersonRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder()
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
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

        Assertions.assertThat(this.personRequestDTO.hashCode())
                .isNotEqualTo(this.differentPersonRequestDTO.hashCode());
    }
}