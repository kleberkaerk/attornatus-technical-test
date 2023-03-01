package com.attornatustechnicaltest.dto.request;

import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class PersonRequestDTO {

    @Pattern(regexp = "[a-zA-ZçÇáÁéÉíÍóÓúÚãÃõÕâÂêÊîÎôÔûÛ\\s]+", message = "Nome inválido, por favor verifique seu nome e tente novamente.")
    private final String name;

    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Data de nascimento inválida, por favor insira sua data de nascimento no formato dd-mm-aaaa.")
    private final String dateOfBirth;

    private PersonRequestDTO(String name, String dateOfBirth) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonRequestDTO that = (PersonRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    public static final class PersonRequestDTOBuilder {
        private String name;
        private String dateOfBirth;

        private PersonRequestDTOBuilder() {
        }

        public static PersonRequestDTOBuilder builder() {

            return new PersonRequestDTOBuilder();
        }

        public PersonRequestDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonRequestDTOBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonRequestDTO build() {

            return new PersonRequestDTO(name, dateOfBirth);
        }
    }
}
