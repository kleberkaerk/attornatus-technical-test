package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persons")
public class PersonController {

    private PersonService personService;

    @Autowired
    protected PersonController(PersonService personService) {

        this.personService = personService;
    }

}
