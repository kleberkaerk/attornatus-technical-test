package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestDTO;
import com.attornatustechnicaltest.dto.response.AddressResponseDTO;
import com.attornatustechnicaltest.service.AddressService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    private AddressResponseDTO addressResponseDTORegisterAddress;

    void setAddressResponseDTORegisterAddress() {

        Person person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        this.addressResponseDTORegisterAddress = AddressResponseDTO.AddressResponseDTOBuilder.builder()
                .id(1L)
                .cep("00000-000")
                .number("1")
                .publicPlace("public place")
                .city("city")
                .isMain(true)
                .person(person)
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setAddressResponseDTORegisterAddress();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(this.addressService.registerAddress(ArgumentMatchers.any(AddressRequestDTO.class)))
                .thenReturn(this.addressResponseDTORegisterAddress);
    }

    @Test
    void registerAddress_returnsTheReturnOfMethodRegisterAddressFromAddressServiceAndAStatusCodeCreated_wheneverCalled() {

        AddressRequestDTO addressRequestDTO = AddressRequestDTO.AddressRequestDTOBuilder.builder().build();

        Assertions.assertThatCode(() -> this.addressController.registerAddress(addressRequestDTO))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.addressController.registerAddress(addressRequestDTO))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(this.addressResponseDTORegisterAddress, HttpStatus.CREATED));
    }
}