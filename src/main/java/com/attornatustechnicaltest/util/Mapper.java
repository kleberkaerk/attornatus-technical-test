package com.attornatustechnicaltest.util;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.dto.response.PersonResponseDTO;

public class Mapper {

    private Mapper() {
    }

    public static Person fromPersonRequestDTOToPerson(PersonRequestDTO personRequestDTO) {

        return Person.PersonBuilder.builder()
                .name(personRequestDTO.getName())
                .dateOfBirth(personRequestDTO.getDateOfBirth())
                .build();
    }

    public static PersonResponseDTO fromPersonToPersonResponseDTO(Person person) {

        return PersonResponseDTO.PersonResponseDTOBuilder.builder()
                .id(person.getId())
                .name(person.getName())
                .dateOfBirth(person.getDateOfBirth())
                .build();
    }
}
