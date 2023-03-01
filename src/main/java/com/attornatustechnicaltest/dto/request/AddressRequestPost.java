package com.attornatustechnicaltest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class AddressRequestPost {

    @NotNull(message = "Id da pessoa inválido, por favor verifique o id da pessoa e tente novamente.")
    private final Long personId;
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido, por favor insira o seu cep no formato 00000-000.")
    private final String cep;
    @Pattern(regexp = "[\\da-zA-Z]+", message = "Número inválido, por favor verifique seu número e tente novamente.")
    private final String number;
    @Pattern(regexp = "[a-zA-ZçÇáÁéÉíÍóÓúÚãÃõÕâÂêÊîÎôÔûÛ\\s]+", message = "Logradouro inválido, por favor verifique seu logradouro e tente novamente.")
    private final String publicPlace;

    @Pattern(regexp = "[a-zA-ZçÇáÁéÉíÍóÓúÚãÃõÕâÂêÊîÎôÔûÛ\\s]+", message = "Cidade inválida, por favor verifique sua cidade e tente novamente.")
    private final String city;

    @JsonProperty("main")
    private final boolean isMain;

    private AddressRequestPost(Long personId, String cep, String number, String publicPlace, String city, boolean isMain) {
        this.personId = personId;
        this.cep = cep;
        this.number = number;
        this.publicPlace = publicPlace;
        this.city = city;
        this.isMain = isMain;
    }

    public Long getPersonId() {
        return personId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRequestPost that = (AddressRequestPost) o;
        return isMain == that.isMain && Objects.equals(personId, that.personId) && Objects.equals(cep, that.cep) && Objects.equals(number, that.number) && Objects.equals(publicPlace, that.publicPlace) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, cep, number, publicPlace, city, isMain);
    }

    public static final class AddressRequestPostBuilder {
        private Long personId;
        private String cep;
        private String number;
        private String publicPlace;
        private String city;
        private boolean isMain;

        private AddressRequestPostBuilder() {
        }

        public static AddressRequestPostBuilder builder() {
            return new AddressRequestPostBuilder();
        }

        public AddressRequestPostBuilder personId(Long personId) {
            this.personId = personId;
            return this;
        }

        public AddressRequestPostBuilder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public AddressRequestPostBuilder number(String number) {
            this.number = number;
            return this;
        }

        public AddressRequestPostBuilder publicPlace(String publicPlace) {
            this.publicPlace = publicPlace;
            return this;
        }

        public AddressRequestPostBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressRequestPostBuilder isMain(boolean isMain) {
            this.isMain = isMain;
            return this;
        }

        public AddressRequestPost build() {
            return new AddressRequestPost(personId, cep, number, publicPlace, city, isMain);
        }
    }
}
