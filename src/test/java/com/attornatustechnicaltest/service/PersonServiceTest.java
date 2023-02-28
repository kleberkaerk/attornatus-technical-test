package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.dto.response.PersonResponseDTO;
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

@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private Person personSave;
    private PersonResponseDTO personResponseDTOToComparisonInRegisterPerson;

    void setPersonSave() {

        this.personSave = Person.PersonBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    void setPersonResponseDTOToComparisonInRegisterPerson() {

        this.personResponseDTOToComparisonInRegisterPerson = PersonResponseDTO.PersonResponseDTOBuilder.builder()
                .id(1L)
                .name("name1")
                .dateOfBirth("01-01-2001")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonSave();
        this.setPersonResponseDTOToComparisonInRegisterPerson();
    }

    @BeforeEach
    void definitionOfBehaviorsToMocks() {

        BDDMockito.when(this.personRepository.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(this.personSave);
    }

    @Test
    void registerPerson_mapsPersonRequestDTOToPersonAndUsesThePersonRepositoryToSaveANewPersonAndMapsTheSavedPersonToAPersonResponseDTOaAndReturnsThePersonResponseDTO_wheneverCalled() {

        PersonRequestDTO personRequestDTO = PersonRequestDTO.PersonRequestDTOBuilder.builder().build();

        Assertions.assertThatCode(() -> this.personService.registerPerson(personRequestDTO))
                .doesNotThrowAnyException();

        Assertions.assertThat(this.personService.registerPerson(personRequestDTO))
                .isNotNull()
                .isEqualTo(this.personResponseDTOToComparisonInRegisterPerson);
    }
}