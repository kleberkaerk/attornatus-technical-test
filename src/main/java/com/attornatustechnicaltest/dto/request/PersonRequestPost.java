package com.attornatustechnicaltest.dto.request;

import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class PersonRequestPost {

    @Pattern(regexp = "[a-zA-ZçÇáÁéÉíÍóÓúÚãÃõÕâÂêÊîÎôÔûÛ\\s]+", message = "Nome inválido, por favor verifique seu nome e tente novamente.")
    private final String name;

    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Data de nascimento inválida, por favor insira sua data de nascimento no formato dd-mm-aaaa.")
    private final String dateOfBirth;

    private PersonRequestPost(String name, String dateOfBirth) {

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
        PersonRequestPost that = (PersonRequestPost) o;
        return Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    public static final class PersonRequestPostBuilder {
        private String name;
        private String dateOfBirth;

        private PersonRequestPostBuilder() {
        }

        public static PersonRequestPostBuilder builder() {

            return new PersonRequestPostBuilder();
        }

        public PersonRequestPostBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonRequestPostBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonRequestPost build() {

            return new PersonRequestPost(name, dateOfBirth);
        }
    }
}
