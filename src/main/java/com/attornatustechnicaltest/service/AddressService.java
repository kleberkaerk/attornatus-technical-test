package com.attornatustechnicaltest.service;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import com.attornatustechnicaltest.dto.request.AddressRequestPost;
import com.attornatustechnicaltest.dto.response.AddressResponse;
import com.attornatustechnicaltest.exception.AddressNotFoundException;
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

    private static final String NON_EXISTENT_PERSON_MESSAGE = "Pessoa inexistente, por favor verifique a pessoa e tente novamente.";

    @Autowired
    protected AddressService(PersonService personService, AddressRepository addressRepository) {

        this.personService = personService;
        this.addressRepository = addressRepository;
    }

    public List<AddressResponse> findPersonAddress(Long personId) {

        Person person = this.personService.findOptionalPersonById(personId)
                .orElseThrow(() -> new NonExistentPersonException(NON_EXISTENT_PERSON_MESSAGE));

        List<Address> personAddresses = this.addressRepository.findByPerson(person);

        if (personAddresses.isEmpty()) {

            throw new AddressNotFoundException("Nenhum endereço não encontrado, verifique se esta pessoa possui algum endereço cadastrado e tente novamente.");
        }

        return personAddresses.stream()
                .map(Mapper::fromAddressToAddressResponse)
                .toList();
    }

    private void deactivateAnotherMainAddress(List<Address> personAddresses) {

        Optional<Address> optionalAddress = personAddresses.stream()
                .filter(Address::isMain)
                .findAny();

        optionalAddress
                .ifPresent(address -> this.addressRepository.updateIsMainById(false, address.getId()));
    }

    public AddressResponse registerAddress(AddressRequestPost addressRequestPost) {

        Person person = this.personService.findOptionalPersonById(addressRequestPost.getPersonId())
                .orElseThrow(() -> new NonExistentPersonException(NON_EXISTENT_PERSON_MESSAGE));

        if (addressRequestPost.isMain()) {

            this.deactivateAnotherMainAddress(this.addressRepository.findByPerson(person));
        }

        Address addressToBeSaved = Mapper.fromAddressRequestPostToAddress(addressRequestPost, person);

        Address savedAddress = this.addressRepository.save(addressToBeSaved);

        return Mapper.fromAddressToAddressResponse(savedAddress);
    }

    public void updateMainAddress(Long personId, Long addressId) {

        Person person = this.personService.findOptionalPersonById(personId)
                .orElseThrow(() -> new NonExistentPersonException(NON_EXISTENT_PERSON_MESSAGE));

        Optional<Address> optionalAddress = this.addressRepository.findByPersonAndId(person, addressId);

        if (optionalAddress.isEmpty()) {

            throw new AddressNotFoundException("Endereço não encontrado, por favor verifique se o endereço existe, ou se ele pertence a esta pessoa, e tente novamente.");
        }

        this.deactivateAnotherMainAddress(this.addressRepository.findByPerson(person));

        this.addressRepository.updateIsMainByPersonAndId(true, person, addressId);
    }
}
