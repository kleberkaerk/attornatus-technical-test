package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.AddressRequestDTO;
import com.attornatustechnicaltest.dto.response.AddressResponseDTO;
import com.attornatustechnicaltest.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    protected AddressController(AddressService addressService) {

        this.addressService = addressService;
    }

    @PostMapping(value = "register")
    public ResponseEntity<AddressResponseDTO> registerAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO) {

        return new ResponseEntity<>(this.addressService.registerAddress(addressRequestDTO), HttpStatus.CREATED);
    }
}
