package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    protected PersonService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }
}
