package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.dto.response.PersonResponseDTO;
import com.attornatustechnicaltest.repository.PersonRepository;
import com.attornatustechnicaltest.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    protected PersonService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    public PersonResponseDTO registerPerson(PersonRequestDTO personRequestDTO) {

        Person personToBeSaved = Mapper.fromPersonRequestDTOToPerson(personRequestDTO);

        Person savedPerson = this.personRepository.save(personToBeSaved);

        return Mapper.fromPersonToPersonResponseDTO(savedPerson);
    }
}
