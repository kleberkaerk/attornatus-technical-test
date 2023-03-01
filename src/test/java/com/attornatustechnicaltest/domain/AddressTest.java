package com.attornatustechnicaltest.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

    private Person person;
    private Address address;
    private Address sameAddress;
    private Address differentAddress;

    void setPerson() {

        this.person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setAddress() {

        this.address = Address.AddressBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(this.person)
                .build();
    }

    void setSameAddress() {

        this.sameAddress = Address.AddressBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(this.person)
                .build();
    }

    void setDifferentAddress() {

        this.differentAddress = Address.AddressBuilder.builder()
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
        this.setAddress();
        this.setSameAddress();
        this.setDifferentAddress();
    }

    @Test
    void getId() {

        Assertions.assertThat(this.address.getId())
                .isEqualTo(1L);
    }

    @Test
    void getCep() {

        Assertions.assertThat(this.address.getCep())
                .isEqualTo("11111-111");
    }

    @Test
    void getNumber() {

        Assertions.assertThat(this.address.getNumber())
                .isEqualTo("1");
    }

    @Test
    void getPublicPlace() {

        Assertions.assertThat(this.address.getPublicPlace())
                .isEqualTo("public place1");
    }

    @Test
    void getCity() {

        Assertions.assertThat(this.address.getCity())
                .isEqualTo("city1");
    }

    @Test
    void isMain() {

        Assertions.assertThat(this.address.isMain())
                .isTrue();
    }

    @Test
    void getPerson() {

        Assertions.assertThat(this.address.getPerson())
                .isEqualTo(this.person);
    }

    @Test
    void testToString() {

        Assertions.assertThat(this.address)
                .hasToString("Address{" +
                        "id=" + this.address.getId() +
                        ", cep='" + this.address.getCep() + '\'' +
                        ", number='" + this.address.getNumber() + '\'' +
                        ", publicPlace='" + this.address.getPublicPlace() + '\'' +
                        ", city='" + this.address.getCity() + '\'' +
                        ", isMain=" + this.address.isMain() +
                        ", person=" + this.address.getPerson() +
                        '}');
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.address)
                .isEqualTo(this.sameAddress)
                .isNotEqualTo(this.differentAddress);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.address)
                .hasSameHashCodeAs(this.sameAddress);

        Assertions.assertThat(this.address.hashCode())
                .isNotEqualTo(this.differentAddress.hashCode());
    }
}