package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.PersonRequestPost;
import com.attornatustechnicaltest.dto.request.PersonRequestPut;
import com.attornatustechnicaltest.dto.response.PersonResponse;
import com.attornatustechnicaltest.exception_response.NonExistentPersonExceptionDetails;
import com.attornatustechnicaltest.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todas as pessoas.", responses = @ApiResponse(responseCode = "200", description = "Se tudo ocorrer bem."))
    public ResponseEntity<Page<PersonResponse>> findAllPerson(@ParameterObject Pageable pageable) {

        return new ResponseEntity<>(this.personService.findAllPerson(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "find-by-id/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca uma pessoa pelo id.", responses = {
            @ApiResponse(responseCode = "200", description = "Se o id da pessoa existir no banco de dados."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Se o id da pessoa não existir no banco de dados.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NonExistentPersonExceptionDetails.class)))
    })
    public ResponseEntity<PersonResponse> findPersonById(@PathVariable @Parameter(example = "1") Long personId) {

        return new ResponseEntity<>(this.personService.findPersonById(personId), HttpStatus.OK);
    }

    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra uma nova pessoa.", responses = @ApiResponse(responseCode = "201", description = "Se tudo ocorrer bem."))
    public ResponseEntity<PersonResponse> registerPerson(@RequestBody @Valid PersonRequestPost personRequestPost) {

        return new ResponseEntity<>(
                this.personService.registerPerson(personRequestPost),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "update")
    @Operation(summary = "Atualiza uma pessoa.", responses = {
            @ApiResponse(responseCode = "204", description = "Se o id da pessoa existir no banco de dados."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Se o id da pessoa não existir no banco de dados.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NonExistentPersonExceptionDetails.class)))
    })
    public ResponseEntity<Void> updatePerson(@RequestBody @Valid PersonRequestPut personRequestPut) {

        this.personService.updatePerson(personRequestPut);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
