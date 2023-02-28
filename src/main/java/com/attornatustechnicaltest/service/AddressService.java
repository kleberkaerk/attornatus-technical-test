package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    protected AddressService(AddressRepository addressRepository) {

        this.addressRepository = addressRepository;
    }
}
