package com.attornatustechnicaltest.dto.response;

import com.attornatustechnicaltest.domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressResponseTest {

    private Person person;
    private AddressResponse addressResponse;
    private AddressResponse sameAddressResponse;
    private AddressResponse differentAddressResponse;

    void setPerson() {

        this.person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setAddressResponse() {

        this.addressResponse = AddressResponse.AddressResponseBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(this.person)
                .build();
    }

    void setSameAddressResponse() {

        this.sameAddressResponse = AddressResponse.AddressResponseBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(this.person)
                .build();
    }

    void setDifferentAddressResponse() {

        this.differentAddressResponse = AddressResponse.AddressResponseBuilder.builder()
                .id(2L)
                .cep("22222-222")
                .number("2")
                .publicPlace("public place2")
                .city("city2")
                .isMain(false)
                .person(Person.PersonBuilder.builder()
                        .id(2L)
                        .name("name2")
                        .dateOfBirth("02-02-2002")
                        .build())
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPerson();
        this.setAddressResponse();
        this.setSameAddressResponse();
        this.setDifferentAddressResponse();
    }

    @Test
    void getId() {

        Assertions.assertThat(this.addressResponse.getId())
                .isEqualTo(1L);
    }

    @Test
    void getCep() {

        Assertions.assertThat(this.addressResponse.getCep())
                .isEqualTo("11111-111");
    }

    @Test
    void getNumber() {

        Assertions.assertThat(this.addressResponse.getNumber())
                .isEqualTo("1");
    }

    @Test
    void getPublicPlace() {

        Assertions.assertThat(this.addressResponse.getPublicPlace())
                .isEqualTo("public place1");
    }

    @Test
    void getCity() {

        Assertions.assertThat(this.addressResponse.getCity())
                .isEqualTo("city1");
    }

    @Test
    void isMain() {

        Assertions.assertThat(this.addressResponse.isMain())
                .isTrue();
    }

    @Test
    void getPerson() {

        Assertions.assertThat(this.addressResponse.getPerson())
                .isEqualTo(this.person);
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.addressResponse)
                .isEqualTo(this.sameAddressResponse)
                .isNotEqualTo(this.differentAddressResponse);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.addressResponse)
                .hasSameHashCodeAs(this.sameAddressResponse);
    }
}