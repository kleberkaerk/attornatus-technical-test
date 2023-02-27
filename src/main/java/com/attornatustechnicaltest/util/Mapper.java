package com.attornatustechnicaltest.util;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestDTO;

public class Mapper {

    private Mapper() {
    }

    public static Person fromPersonRequestDTOToPerson(PersonRequestDTO personRequestDTO) {

        return Person.PersonBuilder.builder()
                .name(personRequestDTO.getName())
                .dateOfBirth(personRequestDTO.getDateOfBirth())
                .build();
    }
}
