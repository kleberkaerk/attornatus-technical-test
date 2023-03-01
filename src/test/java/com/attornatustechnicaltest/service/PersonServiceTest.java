package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.response.PersonResponse;
import com.attornatustechnicaltest.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private Person personSave;
    private PersonResponse personResponseToComparisonInRegisterPerson;
    private Person personFindById;

    void setPersonSave() {

        this.personSave = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setPersonResponseToComparisonInRegisterPerson() {

        this.personResponseToComparisonInRegisterPerson = PersonResponse.PersonResponseBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setPersonFindById() {

        this.personFindById = Person.PersonBuilder.builder()
                .id(2L)
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonSave();
        this.setPersonResponseToComparisonInRegisterPerson();
        this.setPersonFindById();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(this.personRepository.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(this.personSave);

        BDDMockito.when(this.personRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(this.personFindById));
    }

    @Test
    void registerPerson_mapsPersonRequestToPersonAndUsesThePersonRepositoryToSaveANewPersonAndMapsTheSavedPersonToAPersonResponseAndReturnsThePersonResponse_wheneverCalled() {

        PersonRequestPost personRequestPost = PersonRequestPost.PersonRequestPostBuilder.builder().build();

        Assertions.assertThatCode(() -> this.personService.registerPerson(personRequestPost))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personService.registerPerson(personRequestPost))
                .isNotNull()
                .isEqualTo(this.personResponseToComparisonInRegisterPerson);
    }

    @Test
    void findOptionalPersonById_usesThePersonRepositoryToFindPersonByIdAndReturnsAnOptionalPersonFromTheResult_wheneverCalled() {

        Assertions.assertThatCode(() -> this.personService.findOptionalPersonById(2L))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personService.findOptionalPersonById(2L))
                .isNotNull()
                .contains(this.personFindById);
    }
}