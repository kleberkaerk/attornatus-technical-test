package com.attornatustechnicaltest.util;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestPost;
import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.response.AddressResponse;
import com.attornatustechnicaltest.dto.response.PersonResponse;

public class Mapper {

    private Mapper() {
    }

    public static Person fromPersonRequestPostToPerson(PersonRequestPost personRequestPost) {

        return Person.PersonBuilder.builder()
                .name(personRequestPost.getName())
                .dateOfBirth(personRequestPost.getDateOfBirth())
                .build();
    }

    public static PersonResponse fromPersonToPersonResponse(Person person) {

        return PersonResponse.PersonResponseBuilder.builder()
                .id(person.getId())
                .name(person.getName())
                .dateOfBirth(person.getDateOfBirth())
                .build();
    }

    public static Address fromAddressRequestPostToAddress(AddressRequestPost addressRequestPost, Person person) {

        return Address.AddressBuilder.builder()
                .cep(addressRequestPost.getCep())
                .number(addressRequestPost.getNumber())
                .publicPlace(addressRequestPost.getPublicPlace())
                .city(addressRequestPost.getCity())
                .isMain(addressRequestPost.isMain())
                .person(person)
                .build();
    }

    public static AddressResponse fromAddressToAddressResponse(Address address) {

        return AddressResponse.AddressResponseBuilder.builder()
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
