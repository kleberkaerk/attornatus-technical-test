package com.attornatustechnicaltest.controller;

import com.attornatustechnicaltest.dto.request.AddressRequestPost;
import com.attornatustechnicaltest.dto.response.AddressResponse;
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
    public ResponseEntity<AddressResponse> registerAddress(@RequestBody @Valid AddressRequestPost addressRequestPost) {

        return new ResponseEntity<>(this.addressService.registerAddress(addressRequestPost), HttpStatus.CREATED);
    }

    @PutMapping(value = "{personId}/{addressId}")
    public ResponseEntity<Void> updateMainAddress(@PathVariable Long personId, @PathVariable Long addressId) {

        this.addressService.updateMainAddress(personId, addressId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
