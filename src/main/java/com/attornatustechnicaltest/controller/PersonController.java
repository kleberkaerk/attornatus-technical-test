package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.PersonRequestDTO;
import com.attornatustechnicaltest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persons")
public class PersonController {

    private PersonService personService;

    @Autowired
    protected PersonController(PersonService personService) {

        this.personService = personService;
    }

    @PostMapping("register")
    public ResponseEntity<PersonRequestDTO> registerNewPerson(@RequestBody PersonRequestDTO personRequestDTO) {

        return new ResponseEntity<>(personRequestDTO, HttpStatus.CREATED);
    }

}
