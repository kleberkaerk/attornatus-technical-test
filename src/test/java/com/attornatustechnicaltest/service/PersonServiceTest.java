package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.request.PersonRequestPut;
import com.attornatustechnicaltest.dto.response.PersonResponse;
import com.attornatustechnicaltest.exception.NonExistentPersonException;
import com.attornatustechnicaltest.repository.PersonRepository;
import com.attornatustechnicaltest.util.Mapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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
    private List<Person> peopleFindAll;

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

    void setPeopleFindAll() {

        this.peopleFindAll = List.of(
                Person.PersonBuilder.builder()
                        .id(1L)
                        .name("name1")
                        .dateOfBirth("01-01-2001")
                        .build(),
                Person.PersonBuilder.builder()
                        .id(2L)
                        .name("name2")
                        .dateOfBirth("02-02-2002")
                        .build(),
                Person.PersonBuilder.builder()
                        .id(3L)
                        .name("name3")
                        .dateOfBirth("03-03-2003")
                        .build()
        );
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonSave();
        this.setPersonResponseToComparisonInRegisterPerson();
        this.setPersonFindById();
        this.setPeopleFindAll();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(this.personRepository.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(this.personSave);

        BDDMockito.when(this.personRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(this.personFindById));

        BDDMockito.when(this.personRepository.findAll(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(new PageImpl<>(this.peopleFindAll));
    }

    @Test
    void findAllPerson_fetchesAllPeopleAndMapsThemToPersonResponseAndReturnsAPersonResponsePage_wheneverCalled() {

        List<PersonResponse> personResponsesToComparison = this.peopleFindAll.stream()
                .map(Mapper::fromPersonToPersonResponse)
                .toList();

        Assertions.assertThatCode(() -> this.personService.findAllPerson(Page.empty().getPageable()))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personService.findAllPerson(Page.empty().getPageable()))
                .isNotNull()
                .isEqualTo(new PageImpl<>(personResponsesToComparison));
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

    @Test
    void updatePerson_throwsNonExistentPersonException_whenThereIsNoPersonInTheDatabaseWithAnIdEqualToTheValueOfTheIdPropertyFromPersonRequestPut() {

        BDDMockito.when(this.personRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.empty());

        PersonRequestPut personRequestPut = PersonRequestPut.PersonRequestPutBuilder.builder()
                .id(3L)
                .name("name3")
                .dateOfBirth("03-02-2002")
                .build();

        Assertions.assertThatExceptionOfType(NonExistentPersonException.class)
                .isThrownBy(() -> this.personService.updatePerson(personRequestPut));
    }

    @Test
    void updatePerson_mapsFromPersonRequestPutToPersonAndCallsPersonRepositoryToUpdatesThisPersonInDatabase_whenPersonRequestPutIsValid() {

        PersonRequestPut personRequestPut = PersonRequestPut.PersonRequestPutBuilder.builder()
                .id(2L)
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();

        Assertions.assertThatCode(() -> this.personService.updatePerson(personRequestPut))
                .doesNotThrowAnyException();

        Mockito.verify(this.personRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Person.class));

    }
}