package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestPost;
import com.attornatustechnicaltest.dto.response.AddressResponse;
import com.attornatustechnicaltest.exception.NonExistentPersonException;
import com.attornatustechnicaltest.repository.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;
    @Mock
    private PersonService personService;
    @Mock
    private AddressRepository addressRepository;

    private Person personFindOptionalPersonById;
    private AddressResponse addressResponseToComparisonInRegisterAddress1;
    private List<Address> addressesFindByPerson;
    private AddressResponse addressResponseToComparisonInRegisterAddress2;

    void setPersonFindOptionalPersonById() {

        this.personFindOptionalPersonById = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setAddressResponseToComparisonInRegisterAddress1() {

        this.addressResponseToComparisonInRegisterAddress1 = AddressResponse.AddressResponseBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(false)
                .person(this.personFindOptionalPersonById)
                .build();
    }

    void setAddressesFindByPerson() {

        this.addressesFindByPerson = List.of(
                Address.AddressBuilder.builder()
                        .id(1L)
                        .cep("11111-111")
                        .number("1")
                        .publicPlace("public place1")
                        .city("city1")
                        .isMain(false)
                        .person(this.personFindOptionalPersonById)
                        .build(),
                Address.AddressBuilder.builder()
                        .id(2L)
                        .cep("22222-222")
                        .number("2")
                        .publicPlace("public place2")
                        .city("city2")
                        .isMain(true)
                        .person(this.personFindOptionalPersonById)
                        .build(),
                Address.AddressBuilder.builder()
                        .id(3L)
                        .cep("33333-333")
                        .number("3")
                        .publicPlace("public place3")
                        .city("city3")
                        .isMain(false)
                        .person(this.personFindOptionalPersonById)
                        .build()
        );
    }

    void setAddressResponseToComparisonInRegisterAddress2() {

        this.addressResponseToComparisonInRegisterAddress2 = AddressResponse.AddressResponseBuilder.builder()
                .id(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .person(this.personFindOptionalPersonById)
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonFindOptionalPersonById();
        this.setAddressResponseToComparisonInRegisterAddress1();
        this.setAddressesFindByPerson();
        this.setAddressResponseToComparisonInRegisterAddress2();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(this.personService.findOptionalPersonById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(this.personFindOptionalPersonById));

        BDDMockito.when(this.addressRepository.findByPerson(ArgumentMatchers.any(Person.class)))
                .thenReturn(this.addressesFindByPerson);

        BDDMockito.doNothing()
                .when(this.addressRepository).updateIsMainById(ArgumentMatchers.anyBoolean(), ArgumentMatchers.any(Long.class));
    }

    @Test
    void registerAddress_throwsNonExistentPersonException_whenThereIsNoPersonInTheDatabaseWithAnIdEqualToTheValueOfThePersonIdPropertyFromAddressRequestPostParameter() {

        BDDMockito.when(this.personService.findOptionalPersonById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.empty());

        AddressRequestPost addressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder()
                .personId(2L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();

        Assertions.assertThatExceptionOfType(NonExistentPersonException.class)
                .isThrownBy(() -> this.addressService.registerAddress(addressRequestPost));
    }

    @Test
    void registerAddress_registerANewAddressForPersonAndReturnsAnAddressResponseThatRepresentsTheAddressThatWasSaved_whenTheIsMainPropertyFromAddressRequestPostParameterIsFalse() {

        BDDMockito.when(this.addressRepository.save(ArgumentMatchers.any(Address.class)))
                .thenReturn(Address.AddressBuilder.builder()
                        .id(1L)
                        .cep("11111-111")
                        .number("1")
                        .publicPlace("public place1")
                        .city("city1")
                        .isMain(false)
                        .person(this.personFindOptionalPersonById)
                        .build());

        AddressRequestPost addressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(false)
                .build();

        Assertions.assertThatCode(() -> this.addressService.registerAddress(addressRequestPost))
                .doesNotThrowAnyException();

        Mockito.verify(addressRepository, Mockito.times(0)).findByPerson(
                ArgumentMatchers.any()
        );

        Assertions.assertThat(this.addressService.registerAddress(addressRequestPost))
                .isNotNull()
                .isEqualTo(this.addressResponseToComparisonInRegisterAddress1);
    }

    @Test
    void registerAddress_checksIfThereIsAnotherPersonAddressWithTrueIsMainPropertyAndIfThereIsAnAddressWithTrueIsMainPropertySetsItToFalseAndRegisterANewAddressForPersonAndReturnsAnAddressResponseThatRepresentsTheAddressThatWasSaved_whenTheIsMainPropertyFromAddressRequestPostParameterIsTrue() {

        BDDMockito.when(this.addressRepository.save(ArgumentMatchers.any(Address.class)))
                .thenReturn(Address.AddressBuilder.builder()
                        .id(1L)
                        .cep("11111-111")
                        .number("1")
                        .publicPlace("public place1")
                        .city("city1")
                        .isMain(true)
                        .person(this.personFindOptionalPersonById)
                        .build());

        AddressRequestPost addressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();

        Assertions.assertThatCode(() -> this.addressService.registerAddress(addressRequestPost))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.addressService.registerAddress(addressRequestPost))
                .isEqualTo(this.addressResponseToComparisonInRegisterAddress2);

        Mockito.verify(addressRepository, Mockito.times(2)).findByPerson(
                ArgumentMatchers.any()
        );
        Mockito.verify(addressRepository, Mockito.times(2)).updateIsMainById(
                ArgumentMatchers.anyBoolean(),
                ArgumentMatchers.any()
        );
    }
}