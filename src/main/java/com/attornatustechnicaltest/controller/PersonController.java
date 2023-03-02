package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.request.PersonRequestPut;
import com.attornatustechnicaltest.dto.response.PersonResponse;
import com.attornatustechnicaltest.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persons")
@CrossOrigin("*")
public class PersonController {

    private final PersonService personService;

    @Autowired
    protected PersonController(PersonService personService) {

        this.personService = personService;
    }

    @GetMapping(value = "find-all")
    public ResponseEntity<Page<PersonResponse>> findAllPerson(Pageable pageable) {

        return new ResponseEntity<>(this.personService.findAllPerson(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "find-by-id/{personId}")
    public ResponseEntity<PersonResponse> findPersonById(@PathVariable Long personId) {

        return new ResponseEntity<>(this.personService.findPersonById(personId), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<PersonResponse> registerPerson(@RequestBody @Valid PersonRequestPost personRequestPost) {

        return new ResponseEntity<>(
                this.personService.registerPerson(personRequestPost),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "update")
    public ResponseEntity<Void> updatePerson(@RequestBody PersonRequestPut personRequestPut) {

        this.personService.updatePerson(personRequestPut);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
