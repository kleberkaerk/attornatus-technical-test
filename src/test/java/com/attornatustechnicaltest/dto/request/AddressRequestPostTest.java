package com.attornatustechnicaltest.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressRequestDTOTest {

    private AddressRequestDTO addressRequestDTO;
    private AddressRequestDTO sameAddressRequestDTO;
    private AddressRequestDTO differentAddressRequestDTO;

    void setAddressRequestDTO() {

        this.addressRequestDTO = AddressRequestDTO.AddressRequestDTOBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();
    }

    void setSameAddressRequestDTO() {

        this.sameAddressRequestDTO = AddressRequestDTO.AddressRequestDTOBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();
    }

    void setDifferentAddressRequestDTO() {

        this.differentAddressRequestDTO = AddressRequestDTO.AddressRequestDTOBuilder.builder()
                .personId(2L)
                .cep("22222-222")
                .number("2")
                .publicPlace("public place2")
                .city("city2")
                .isMain(false)
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setAddressRequestDTO();
        this.setSameAddressRequestDTO();
        this.setDifferentAddressRequestDTO();
    }

    @Test
    void getPersonId() {

        Assertions.assertThat(this.addressRequestDTO.getPersonId())
                .isEqualTo(1L);
    }

    @Test
    void getCep() {

        Assertions.assertThat(this.addressRequestDTO.getCep())
                .isEqualTo("11111-111");
    }

    @Test
    void getNumber() {

        Assertions.assertThat(this.addressRequestDTO.getNumber())
                .isEqualTo("1");
    }

    @Test
    void getPublicPlace() {

        Assertions.assertThat(this.addressRequestDTO.getPublicPlace())
                .isEqualTo("public place1");
    }

    @Test
    void getCity() {

        Assertions.assertThat(this.addressRequestDTO.getCity())
                .isEqualTo("city1");
    }

    @Test
    void isMain() {

        Assertions.assertThat(this.addressRequestDTO.isMain())
                .isTrue();
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.addressRequestDTO)
                .isEqualTo(this.sameAddressRequestDTO)
                .isNotEqualTo(this.differentAddressRequestDTO);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.addressRequestDTO)
                .hasSameHashCodeAs(this.sameAddressRequestDTO);
    }
}