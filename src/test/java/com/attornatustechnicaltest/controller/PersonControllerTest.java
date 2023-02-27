package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.dto.response.PersonResponseDTO;
import com.attornatustechnicaltest.service.PersonService;
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
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    private PersonResponseDTO personResponseDTORegisterPerson;

    private void setPersonResponseDTORegisterPerson() {

        this.personResponseDTORegisterPerson = PersonResponseDTO.PersonResponseDTOBuilder.builder()
                .id(1L)
                .name("name")
                .dateOfBirth("01-01-2000")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonResponseDTORegisterPerson();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(personService.registerPerson(ArgumentMatchers.any(PersonRequestDTO.class)))
                .thenReturn(this.personResponseDTORegisterPerson);
    }

    @Test
    void registerPerson_returnsTheReturnOfMethodRegisterPersonFromPersonServiceAndAStatusCodeCreated_wheneverCalled() {

        PersonRequestDTO personRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder().build();

        Assertions.assertThatCode(() -> this.personController.registerPerson(personRequestDTO))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personController.registerPerson(personRequestDTO))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(this.personResponseDTORegisterPerson, HttpStatus.CREATED));
    }
}