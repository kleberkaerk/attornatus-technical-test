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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    private PersonResponse personResponseRegisterPerson;

    private List<PersonResponse> peopleFindAllPerson;

    void setPersonResponseRegisterPerson() {

        this.personResponseRegisterPerson = PersonResponse.PersonResponseBuilder.builder()
                .id(1L)
                .name("name")
                .dateOfBirth("01-01-2000")
                .build();
    }

    void setPeopleFindAllPerson() {

        this.peopleFindAllPerson = List.of(
                PersonResponse.PersonResponseBuilder.builder()
                        .id(1L)
                        .name("name1")
                        .dateOfBirth("01-01-2001")
                        .build(),
                PersonResponse.PersonResponseBuilder.builder()
                        .id(2L)
                        .name("name2")
                        .dateOfBirth("02-02-2002")
                        .build(),
                PersonResponse.PersonResponseBuilder.builder()
                        .id(3L)
                        .name("name3")
                        .dateOfBirth("03-03-2003")
                        .build()
        );
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonResponseRegisterPerson();
        this.setPeopleFindAllPerson();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(personService.registerPerson(ArgumentMatchers.any(PersonRequestPost.class)))
                .thenReturn(this.personResponseRegisterPerson);

        BDDMockito.doNothing()
                .when(this.personService).updatePerson(ArgumentMatchers.any(PersonRequestPut.class));

        BDDMockito.when(this.personService.findAllPerson(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(new PageImpl<>(this.peopleFindAllPerson));
    }

    @Test
    void findAllPerson_returnsTheReturnOfMethodFindAllPersonFromPersonServiceAndAStatusCodeOk_whenPersonServiceThrowsNoException() {

        Assertions.assertThatCode(() -> this.personController.findAllPerson(Page.empty().getPageable()))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personController.findAllPerson(Page.empty().getPageable()))
                .isNotNull()
                .isEqualTo(new ResponseEntity<>(new PageImpl<>(this.peopleFindAllPerson), HttpStatus.OK));
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