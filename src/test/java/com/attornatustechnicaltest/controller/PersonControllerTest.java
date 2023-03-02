package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.request.PersonRequestPut;
import com.attornatustechnicaltest.dto.response.PersonResponse;
import com.attornatustechnicaltest.service.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    private PersonResponse personResponseRegisterPerson;

    private void setPersonResponseRegisterPerson() {

        this.personResponseRegisterPerson = PersonResponse.PersonResponseBuilder.builder()
                .id(1L)
                .name("name")
                .dateOfBirth("01-01-2000")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonResponseRegisterPerson();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(personService.registerPerson(ArgumentMatchers.any(PersonRequestPost.class)))
                .thenReturn(this.personResponseRegisterPerson);

        BDDMockito.doNothing()
                .when(this.personService).updatePerson(ArgumentMatchers.any(PersonRequestPut.class));
    }

    @Test
    void registerPerson_returnsTheReturnOfMethodRegisterPersonFromPersonServiceAndAStatusCodeCreated_whenPersonServiceThrowsNoException() {

        PersonRequestPost personRequestPost = PersonRequestPost.PersonRequestPostBuilder.builder().build();

        Assertions.assertThatCode(() -> this.personController.registerPerson(personRequestPost))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personController.registerPerson(personRequestPost))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(this.personResponseRegisterPerson, HttpStatus.CREATED));
    }

    @Test
    void updatePerson_returnsAStatusCodeNoContent_whenPersonServiceThrowsNoException() {

        PersonRequestPut personRequestPut = PersonRequestPut.PersonRequestPutBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();

        Assertions.assertThatCode(() -> this.personController.updatePerson(personRequestPut))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personController.updatePerson(personRequestPut))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        Mockito.verify(this.personService, Mockito.times(2))
                .updatePerson(ArgumentMatchers.any(PersonRequestPut.class));
    }
}