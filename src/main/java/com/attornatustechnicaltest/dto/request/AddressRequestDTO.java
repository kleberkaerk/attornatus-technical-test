package com.attornatustechnicaltest.dto.request;

import java.util.Objects;

public class AddressRequestDTO {

    private final Long personId;
    private final String cpf;
    private final String number;
    private final String publicPlace;
    private final String city;
    private final boolean isMain;

    private AddressRequestDTO(Long personId, String cpf, String number, String publicPlace, String city, boolean isMain) {
        this.personId = personId;
        this.cpf = cpf;
        this.number = number;
        this.publicPlace = publicPlace;
        this.city = city;
        this.isMain = isMain;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getCpf() {
        return cpf;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRequestDTO that = (AddressRequestDTO) o;
        return isMain == that.isMain &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(number, that.number) &&
                Objects.equals(publicPlace, that.publicPlace) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, cpf, number, publicPlace, city, isMain);
    }

    public static final class AddressRequestDTOBuilder {
        private Long personId;
        private String cpf;
        private String number;
        private String publicPlace;
        private String city;
        private boolean isMain;

        private AddressRequestDTOBuilder() {
        }

        public static AddressRequestDTOBuilder builder() {
            return new AddressRequestDTOBuilder();
        }

        public AddressRequestDTOBuilder personId(Long personId) {
            this.personId = personId;
            return this;
        }

        public AddressRequestDTOBuilder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public AddressRequestDTOBuilder number(String number) {
            this.number = number;
            return this;
        }

        public AddressRequestDTOBuilder publicPlace(String publicPlace) {
            this.publicPlace = publicPlace;
            return this;
        }

        public AddressRequestDTOBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressRequestDTOBuilder isMain(boolean isMain) {
            this.isMain = isMain;
            return this;
        }

        public AddressRequestDTO build() {
            return new AddressRequestDTO(personId, cpf, number, publicPlace, city, isMain);
        }
    }
}
