package com.attornatustechnicaltest.util;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestPost;
import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.response.AddressResponse;
import com.attornatustechnicaltest.dto.response.PersonResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapperTest {

    private Person personToComparisonInFromPersonRequestPostToPerson;
    private PersonResponse personResponseToComparisonInFromPersonToPersonResponse;
    private Address addressToComparisonInFromAddressRequestPostToAddress;
    private AddressResponse addressResponseToComparisonInFromAddressToAddressResponse;

    void setPersonToComparisonInFromPersonRequestPostToPerson() {

        this.personToComparisonInFromPersonRequestPostToPerson = Person.PersonBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setPersonResponseToComparisonInFromPersonToPersonResponse() {

        this.personResponseToComparisonInFromPersonToPersonResponse =
                PersonResponse.PersonResponseBuilder.builder()
                        .id(1L)
                        .name("name1")
                        .dateOfBirth("01-01-2001")
                        .build();
    }

    void setAddressToComparisonInFromAddressRequestPostToAddress() {

        this.addressToComparisonInFromAddressRequestPostToAddress = Address.AddressBuilder.builder()
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

    void setAddressResponseToComparisonInFromAddressToAddressResponse() {

        this.addressResponseToComparisonInFromAddressToAddressResponse =
                AddressResponse.AddressResponseBuilder.builder()
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

        this.setPersonToComparisonInFromPersonRequestPostToPerson();
        this.setPersonResponseToComparisonInFromPersonToPersonResponse();
        this.setAddressToComparisonInFromAddressRequestPostToAddress();
        this.setAddressResponseToComparisonInFromAddressToAddressResponse();
    }

    @Test
    void fromPersonRequestPostToPerson_mapsFromPersonRequestPostToPerson_wheneverCalled() {

        PersonRequestPost personRequestPost = PersonRequestPost.PersonRequestPostBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        Assertions.assertThat(Mapper.fromPersonRequestPostToPerson(personRequestPost))
                .isNotNull()
                .isEqualTo(this.personToComparisonInFromPersonRequestPostToPerson);
    }

    @Test
    void fromPersonToPersonResponse_mapsFromPersonToPersonResponse_wheneverCalled() {

        Person person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        Assertions.assertThat(Mapper.fromPersonToPersonResponse(person))
                .isNotNull()
                .isEqualTo(this.personResponseToComparisonInFromPersonToPersonResponse);
    }

    @Test
    void fromAddressRequestPostToAddress_mapsFromAddressRequestPostToAddress_wheneverCalled() {

        Person person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        AddressRequestPost addressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();

        Assertions.assertThat(Mapper.fromAddressRequestPostToAddress(addressRequestPost, person))
                .isNotNull()
                .isEqualTo(this.addressToComparisonInFromAddressRequestPostToAddress);
    }

    @Test
    void fromAddressToAddressResponse_mapsFromAddressToAddressResponse_wheneverCalled() {

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

        Assertions.assertThat(Mapper.fromAddressToAddressResponse(address))
                .isNotNull()
                .isEqualTo(this.addressResponseToComparisonInFromAddressToAddressResponse);
    }
}