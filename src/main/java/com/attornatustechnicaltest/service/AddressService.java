package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestDTO;
import com.attornatustechnicaltest.dto.response.AddressResponseDTO;
import com.attornatustechnicaltest.exception.NonExistentPersonException;
import com.attornatustechnicaltest.repository.AddressRepository;
import com.attornatustechnicaltest.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final PersonService personService;
    private final AddressRepository addressRepository;

    @Autowired
    protected AddressService(PersonService personService, AddressRepository addressRepository) {

        this.personService = personService;
        this.addressRepository = addressRepository;
    }

    private void deactivateAnotherMainAddress(List<Address> personAddresses) {

        Optional<Address> optionalAddress = personAddresses.stream()
                .filter(Address::isMain)
                .findAny();

        optionalAddress
                .ifPresent(address -> this.addressRepository.updateIsMainById(false, address.getId()));
    }

    public AddressResponseDTO registerAddress(AddressRequestDTO addressRequestDTO) {

        Person person = this.personService.findOptionalPersonById(addressRequestDTO.getPersonId())
                .orElseThrow(() -> new NonExistentPersonException("Pessoa inexistente, por favor verifique a pessoa e tente novamente."));

        if (addressRequestDTO.isMain()) {

            this.deactivateAnotherMainAddress(this.addressRepository.findByPerson(person));
        }

        Address addressToBeSaved = Mapper.fromAddressRequestDTOToAddress(addressRequestDTO, person);

        Address savedAddress = this.addressRepository.save(addressToBeSaved);

        return Mapper.fromAddressToAddressResponseDTO(savedAddress);
    }
}
