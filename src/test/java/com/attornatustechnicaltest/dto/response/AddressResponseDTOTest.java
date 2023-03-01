package com.attornatustechnicaltest.dto.response;

import com.attornatustechnicaltest.domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressResponseDTOTest {

    private Person person;
    private AddressResponseDTO addressResponseDTO;
    private AddressResponseDTO sameAddressResponseDTO;
    private AddressResponseDTO differentAddressResponseDTO;

    void setPerson() {

        this.person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setAddressResponseDTO() {

        this.addressResponseDTO = AddressResponseDTO.AddressResponseDTOBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(this.person)
                .build();
    }

    void setSameAddressResponseDTO() {

        this.sameAddressResponseDTO = AddressResponseDTO.AddressResponseDTOBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(this.person)
                .build();
    }

    void setDifferentAddressResponseDTO() {

        this.differentAddressResponseDTO = AddressResponseDTO.AddressResponseDTOBuilder.builder()
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
        this.setAddressResponseDTO();
        this.setSameAddressResponseDTO();
        this.setDifferentAddressResponseDTO();
    }

    @Test
    void getId() {

        Assertions.assertThat(this.addressResponseDTO.getId())
                .isEqualTo(1L);
    }

    @Test
    void getCep() {

        Assertions.assertThat(this.addressResponseDTO.getCep())
                .isEqualTo("11111-111");
    }

    @Test
    void getNumber() {

        Assertions.assertThat(this.addressResponseDTO.getNumber())
                .isEqualTo("1");
    }

    @Test
    void getPublicPlace() {

        Assertions.assertThat(this.addressResponseDTO.getPublicPlace())
                .isEqualTo("public place1");
    }

    @Test
    void getCity() {

        Assertions.assertThat(this.addressResponseDTO.getCity())
                .isEqualTo("city1");
    }

    @Test
    void isMain() {

        Assertions.assertThat(this.addressResponseDTO.isMain())
                .isTrue();
    }

    @Test
    void getPerson() {

        Assertions.assertThat(this.addressResponseDTO.getPerson())
                .isEqualTo(this.person);
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.addressResponseDTO)
                .isEqualTo(this.sameAddressResponseDTO)
                .isNotEqualTo(this.differentAddressResponseDTO);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.addressResponseDTO)
                .hasSameHashCodeAs(this.sameAddressResponseDTO);
    }
}