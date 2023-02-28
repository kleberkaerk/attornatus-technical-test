package com.attornatustechnicaltest.util;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestDTO;
import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.dto.response.AddressResponseDTO;
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

    public static Address fromAddressRequestDTOToAddress(AddressRequestDTO addressRequestDTO, Person person) {

        return Address.AddressBuilder.builder()
                .cep(addressRequestDTO.getCep())
                .number(addressRequestDTO.getNumber())
                .publicPlace(addressRequestDTO.getPublicPlace())
                .city(addressRequestDTO.getCity())
                .isMain(addressRequestDTO.isMain())
                .person(person)
                .build();
    }

    public static AddressResponseDTO fromAddressToAddressResponseDTO(Address address) {

        return AddressResponseDTO.AddressResponseDTOBuilder.builder()
                .id(address.getId())
                .cep(address.getCep())
                .number(address.getNumber())
                .publicPlace(address.getPublicPlace())
                .city(address.getCity())
                .isMain(address.isMain())
                .person(address.getPerson())
                .build();
    }
}
