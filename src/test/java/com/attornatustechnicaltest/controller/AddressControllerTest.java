package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestPost;
import com.attornatustechnicaltest.dto.response.AddressResponse;
import com.attornatustechnicaltest.service.AddressService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    private AddressResponse addressResponseRegisterAddress;

    void setAddressResponseRegisterAddress() {

        Person person = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        this.addressResponseRegisterAddress = AddressResponse.AddressResponseBuilder.builder()
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

        this.setAddressResponseRegisterAddress();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(this.addressService.registerAddress(ArgumentMatchers.any(AddressRequestPost.class)))
                .thenReturn(this.addressResponseRegisterAddress);

        BDDMockito.doNothing()
                .when(this.addressService).updateMainAddress(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class));
    }

    @Test
    void registerAddress_returnsTheReturnOfMethodRegisterAddressFromAddressServiceAndAStatusCodeCreated_wheneverCalled() {

        AddressRequestPost addressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder().build();

        Assertions.assertThatCode(() -> this.addressController.registerAddress(addressRequestPost))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.addressController.registerAddress(addressRequestPost))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(this.addressResponseRegisterAddress, HttpStatus.CREATED));
    }

    @Test
    void updateMainAddress_ReturnsAStatusCodeNoContent_wheneverCalled() {

        Assertions.assertThatCode(() -> this.addressController.updateMainAddress(1L, 1L))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.addressController.updateMainAddress(1L, 1L))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        Mockito.verify(this.addressService, Mockito.times(2))
                .updateMainAddress(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class));
    }
}