package com.attornatustechnicaltest.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cep")
    private String cep;
    @Column(name = "number")
    private String number;
    @Column(name = "public_place")
    private String publicPlace;
    @Column(name = "city")
    private String city;
    @Column(name = "main")
    private boolean isMain;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    protected Address() {
    }

    public Address(Long id, String cep, String number, String publicPlace, String city, boolean isMain, Person person) {
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
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", number='" + number + '\'' +
                ", publicPlace='" + publicPlace + '\'' +
                ", city='" + city + '\'' +
                ", isMain=" + isMain +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return isMain == address.isMain &&
                Objects.equals(id, address.id) &&
                Objects.equals(cep, address.cep) &&
                Objects.equals(number, address.number) &&
                Objects.equals(publicPlace, address.publicPlace) &&
                Objects.equals(city, address.city) && Objects.equals(person, address.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cep, number, publicPlace, city, isMain, person);
    }

    public static final class AddressBuilder {
        private Long id;
        private String cep;
        private String number;
        private String publicPlace;
        private String city;
        private boolean isMain;
        private Person person;

        private AddressBuilder() {
        }

        public static AddressBuilder builder() {
            return new AddressBuilder();
        }

        public AddressBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AddressBuilder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public AddressBuilder number(String number) {
            this.number = number;
            return this;
        }

        public AddressBuilder publicPlace(String publicPlace) {
            this.publicPlace = publicPlace;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder isMain(boolean isMain) {
            this.isMain = isMain;
            return this;
        }

        public AddressBuilder person(Person person) {
            this.person = person;
            return this;
        }

        public Address build() {
            return new Address(id, cep, number, publicPlace, city, isMain, person);
        }
    }
}
