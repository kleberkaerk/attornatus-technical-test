package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.response.PersonResponse;
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
    }

    @Test
    void registerPerson_returnsTheReturnOfMethodRegisterPersonFromPersonServiceAndAStatusCodeCreated_wheneverCalled() {

        PersonRequestPost personRequestPost = PersonRequestPost.PersonRequestPostBuilder.builder().build();

        Assertions.assertThatCode(() -> this.personController.registerPerson(personRequestPost))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personController.registerPerson(personRequestPost))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(this.personResponseRegisterPerson, HttpStatus.CREATED));
    }
}