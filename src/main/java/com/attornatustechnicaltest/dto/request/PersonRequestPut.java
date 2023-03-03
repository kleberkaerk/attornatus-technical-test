package com.attornatustechnicaltest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class PersonRequestPut {

    @NotNull(message = "Id inválido, por favor verifique o id da pessoa e tente novamente.")
    @Schema(description = "Id da pessoa", example = "1")
    private final Long id;
    @Pattern(regexp = "[a-zA-ZçÇáÁéÉíÍóÓúÚãÃõÕâÂêÊîÎôÔûÛ\\s]+", message = "Nome inválido, por favor verifique seu nome e tente novamente.")
    @Schema(description = "Nome da pessoa", example = "João")
    private final String name;
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Data de nascimento inválida, por favor insira sua data de nascimento no formato dd-mm-aaaa.")
    @Schema(description = "Data de nascimento da pessoa", example = "01-01-2001")
    private final String dateOfBirth;

    private PersonRequestPut(Long id, String name, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
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
        PersonRequestPut that = (PersonRequestPut) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth);
    }

    public static final class PersonRequestPutBuilder {
        private Long id;
        private String name;
        private String dateOfBirth;

        private PersonRequestPutBuilder() {
        }

        public static PersonRequestPutBuilder builder() {
            return new PersonRequestPutBuilder();
        }

        public PersonRequestPutBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PersonRequestPutBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonRequestPutBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonRequestPut build() {
            return new PersonRequestPut(id, name, dateOfBirth);
        }
    }
}
