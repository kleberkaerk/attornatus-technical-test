package com.attornatustechnicaltest.util;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.dto.response.PersonResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapperTest {

    private Person personToComparisonInFromPersonRequestDTOToPerson;
    private PersonResponseDTO personResponseDTOToComparisonInFromPersonToPersonResponseDTO;

    void setPersonToComparisonInFromPersonRequestDTOToPerson() {

        this.personToComparisonInFromPersonRequestDTOToPerson = Person.PersonBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setPersonResponseDTOToComparisonInFromPersonToPersonResponseDTO() {

        this.personResponseDTOToComparisonInFromPersonToPersonResponseDTO =
                PersonResponseDTO.PersonResponseDTOBuilder.builder()
                        .id(1L)
                        .name("name1")
                        .dateOfBirth("01-01-2001")
                        .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonToComparisonInFromPersonRequestDTOToPerson();
        this.setPersonResponseDTOToComparisonInFromPersonToPersonResponseDTO();
    }

    @Test
    void fromPersonRequestDTOToPerson_mapsFromPersonRequestDTOToPerson_wheneverCalled() {

        PersonRequestDTO personRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        Assertions.assertThat(Mapper.fromPersonRequestDTOToPerson(personRequestDTO))
                .isNotNull()
                .isEqualTo(this.personToComparisonInFromPersonRequestDTOToPerson);
    }

    @Test
    void fromPersonToPersonResponseDTO_mapsFromPersonToPersonResponseDTO_wheneverCalled() {

        Person person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        Assertions.assertThat(Mapper.fromPersonToPersonResponseDTO(person))
                .isNotNull()
                .isEqualTo(this.personResponseDTOToComparisonInFromPersonToPersonResponseDTO);
    }
}