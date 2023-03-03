package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.AddressRequestPost;
import com.attornatustechnicaltest.dto.response.AddressResponse;
import com.attornatustechnicaltest.exception_response.AddressNotFoundExceptionDetails;
import com.attornatustechnicaltest.exception_response.NonExistentPersonExceptionDetails;
import com.attornatustechnicaltest.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("addresses")
@CrossOrigin("*")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    protected AddressController(AddressService addressService) {

        this.addressService = addressService;
    }

    @GetMapping(value = "find-by-person/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca os endereços de uma pessoa.", responses = {
            @ApiResponse(responseCode = "204", description = "Se o id da pessoa existir no banco de dados."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Se a pessoa não possuir endereços cadastrados no banco de dados.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressNotFoundExceptionDetails.class)))
    })
    public ResponseEntity<List<AddressResponse>> findPersonAddress(@PathVariable @Parameter(example = "1") Long personId) {

        return new ResponseEntity<>(this.addressService.findPersonAddress(personId), HttpStatus.OK);
    }

    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra um novo endereço para pessoa.", responses = {
            @ApiResponse(responseCode = "204", description = "Se o id da pessoa existir no banco de dados."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Se não existir uma pessoa com o id informado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NonExistentPersonExceptionDetails.class)))
    })
    public ResponseEntity<AddressResponse> registerAddress(@RequestBody @Valid AddressRequestPost addressRequestPost) {

        return new ResponseEntity<>(this.addressService.registerAddress(addressRequestPost), HttpStatus.CREATED);
    }

    @PutMapping(value = "{personId}/{addressId}")
    @Operation(summary = "Atualiza o endereço principal da pessoa.", responses = {
            @ApiResponse(responseCode = "204", description = "Se o id da pessoa e o endereço existirem no banco de dados, e se o endereço pertencer a esta pessoa."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Se o endereço não existir, ou se a pessoa não for a dona do endereço informado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressNotFoundExceptionDetails.class)))
    })
    public ResponseEntity<Void> updateMainAddress(@PathVariable @Parameter(example = "1") Long personId, @PathVariable @Parameter(example = "1") Long addressId) {

        this.addressService.updateMainAddress(personId, addressId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
