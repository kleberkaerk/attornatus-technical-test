package com.attornatustechnicaltest.dto.response;

import com.attornatustechnicaltest.domain.Person;

import java.util.Objects;

public class AddressResponse {

    private final Long id;
    private final String cep;
    private final String number;
    private final String publicPlace;
    private final String city;
    private final boolean isMain;
    private final Person person;

    private AddressResponse(Long id, String cep, String number, String publicPlace, String city, boolean isMain, Person person) {
        this.id = id;
        this.cep = cep;
        this.number = number;
        this.publicPlace = publicPlace;
        this.city = city;
        this.isMain = isMain;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getNumber() {
        return number;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public String getCity() {
        return city;
    }

    public boolean isMain() {
        return isMain;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressResponse that = (AddressResponse) o;
        return isMain == that.isMain &&
                Objects.equals(id, that.id) &&
                Objects.equals(cep, that.cep) &&
                Objects.equals(number, that.number) &&
                Objects.equals(publicPlace, that.publicPlace) &&
                Objects.equals(city, that.city) &&
                Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cep, number, publicPlace, city, isMain, person);
    }

    public static final class AddressResponseBuilder {
        private Long id;
        private String cep;
        private String number;
        private String publicPlace;
        private String city;
        private boolean isMain;
        private Person person;

        private AddressResponseBuilder() {
        }

        public static AddressResponseBuilder builder() {
            return new AddressResponseBuilder();
        }

        public AddressResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AddressResponseBuilder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public AddressResponseBuilder number(String number) {
            this.number = number;
            return this;
        }

        public AddressResponseBuilder publicPlace(String publicPlace) {
            this.publicPlace = publicPlace;
            return this;
        }

        public AddressResponseBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressResponseBuilder isMain(boolean isMain) {
            this.isMain = isMain;
            return this;
        }

        public AddressResponseBuilder person(Person person) {
            this.person = person;
            return this;
        }

        public AddressResponse build() {
            return new AddressResponse(id, cep, number, publicPlace, city, isMain, person);
        }
    }
}
