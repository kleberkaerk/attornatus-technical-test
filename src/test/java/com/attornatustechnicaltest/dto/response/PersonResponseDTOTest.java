package com.attornatustechnicaltest.dto.response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonResponseDTOTest {

    private PersonResponseDTO personResponseDTO;
    private PersonResponseDTO samePersonResponseDTO;
    private PersonResponseDTO differentPersonResponseDTO;

    void setPersonResponseDTO() {

        this.personResponseDTO = PersonResponseDTO.PersonResponseDTOBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setSamePersonResponseDTO() {

        this.samePersonResponseDTO = PersonResponseDTO.PersonResponseDTOBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setDifferentPersonResponseDTO() {

        this.differentPersonResponseDTO = PersonResponseDTO.PersonResponseDTOBuilder.builder()
                .id(2L)
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonResponseDTO();
        this.setSamePersonResponseDTO();
        this.setDifferentPersonResponseDTO();
    }

    @Test
    void getId() {

        Assertions.assertThat(this.personResponseDTO.getId())
                .isNotNull()
                .isEqualTo(1L);
    }

    @Test
    void getName() {

        Assertions.assertThat(this.personResponseDTO.getName())
                .isNotNull()
                .isEqualTo("name1");
    }

    @Test
    void getDateOfBirth() {

        Assertions.assertThat(this.personResponseDTO.getDateOfBirth())
                .isNotNull()
                .isEqualTo("01-01-2001");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.personResponseDTO)
                .isEqualTo(this.samePersonResponseDTO)
                .isNotEqualTo(this.differentPersonResponseDTO);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.personResponseDTO)
                .hasSameHashCodeAs(this.samePersonResponseDTO);

        Assertions.assertThat(this.personResponseDTO.hashCode())
                .isNotEqualTo(this.differentPersonResponseDTO.hashCode());
    }
}