package com.attornatustechnicaltest.util;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestDTO;
import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.dto.response.AddressResponseDTO;
import com.attornatustechnicaltest.dto.response.PersonResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapperTest {

    private Person personToComparisonInFromPersonRequestDTOToPerson;
    private PersonResponseDTO personResponseDTOToComparisonInFromPersonToPersonResponseDTO;
    private Address addressToComparisonInFromAddressRequestDTOToAddress;
    private AddressResponseDTO addressResponseDTOToComparisonInFromAddressToAddressResponseDTO;

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

    void setAddressToComparisonInFromAddressRequestDTOToAddress() {

        this.addressToComparisonInFromAddressRequestDTOToAddress = Address.AddressBuilder.builder()
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(Person.PersonBuilder.builder()
                        .id(1L)
                        .name("name1")
                        .dateOfBirth("01-01-2001")
                        .build())
                .build();
    }

    void setAddressResponseDTOToComparisonInFromAddressToAddressResponseDTO() {

        this.addressResponseDTOToComparisonInFromAddressToAddressResponseDTO =
                AddressResponseDTO.AddressResponseDTOBuilder.builder()
                .id(1L)
                .cep("22222-222")
                .number("2")
                .publicPlace("public place2")
                .city("city2")
                .isMain(true)
                .person(Person.PersonBuilder.builder()
                        .id(2L)
                        .name("name2")
                        .dateOfBirth("02-02-2002")
                        .build())
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonToComparisonInFromPersonRequestDTOToPerson();
        this.setPersonResponseDTOToComparisonInFromPersonToPersonResponseDTO();
        this.setAddressToComparisonInFromAddressRequestDTOToAddress();
        this.setAddressResponseDTOToComparisonInFromAddressToAddressResponseDTO();
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

    @Test
    void fromAddressRequestDTOToAddress_mapsFromAddressRequestDTOToAddress_wheneverCalled() {

        Person person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        AddressRequestDTO addressRequestDTO = AddressRequestDTO.AddressRequestDTOBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();

        Assertions.assertThat(Mapper.fromAddressRequestDTOToAddress(addressRequestDTO, person))
                .isNotNull()
                .isEqualTo(this.addressToComparisonInFromAddressRequestDTOToAddress);
    }

    @Test
    void fromAddressToAddressResponseDTO_mapsFromAddressToAddressResponseDTO_wheneverCalled() {

        Address address = Address.AddressBuilder.builder()
                .id(1L)
                .cep("22222-222")
                .number("2")
                .publicPlace("public place2")
                .city("city2")
                .isMain(true)
                .person(Person.PersonBuilder.builder()
                        .id(2L)
                        .name("name2")
                        .dateOfBirth("02-02-2002")
                        .build())
                .build();

        Assertions.assertThat(Mapper.fromAddressToAddressResponseDTO(address))
                .isNotNull()
                .isEqualTo(this.addressResponseDTOToComparisonInFromAddressToAddressResponseDTO);
    }
}