package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.response.PersonResponse;
import com.attornatustechnicaltest.repository.PersonRepository;
import com.attornatustechnicaltest.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    protected PersonService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    public PersonResponse registerPerson(PersonRequestPost personRequestPost) {

        Person personToBeSaved = Mapper.fromPersonRequestPostToPerson(personRequestPost);

        Person savedPerson = this.personRepository.save(personToBeSaved);

        return Mapper.fromPersonToPersonResponse(savedPerson);
    }

    protected Optional<Person> findOptionalPersonById(Long personId) {

        return this.personRepository.findById(personId);
    }
}
