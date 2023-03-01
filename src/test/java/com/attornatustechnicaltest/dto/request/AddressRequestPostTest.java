package com.attornatustechnicaltest.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressRequestPostTest {

    private AddressRequestPost addressRequestPost;
    private AddressRequestPost sameAddressRequestPost;
    private AddressRequestPost differentAddressRequestPost;

    void setAddressRequestPost() {

        this.addressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();
    }

    void setSameAddressRequestPost() {

        this.sameAddressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder()
                .personId(1L)
                .cep("11111-111")
                .number("1")
                .publicPlace("public place1")
                .city("city1")
                .isMain(true)
                .build();
    }

    void setDifferentAddressRequestPost() {

        this.differentAddressRequestPost = AddressRequestPost.AddressRequestPostBuilder.builder()
                .personId(2L)
                .cep("22222-222")
                .number("2")
                .publicPlace("public place2")
                .city("city2")
                .isMain(false)
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setAddressRequestPost();
        this.setSameAddressRequestPost();
        this.setDifferentAddressRequestPost();
    }

    @Test
    void getPersonId() {

        Assertions.assertThat(this.addressRequestPost.getPersonId())
                .isEqualTo(1L);
    }

    @Test
    void getCep() {

        Assertions.assertThat(this.addressRequestPost.getCep())
                .isEqualTo("11111-111");
    }

    @Test
    void getNumber() {

        Assertions.assertThat(this.addressRequestPost.getNumber())
                .isEqualTo("1");
    }

    @Test
    void getPublicPlace() {

        Assertions.assertThat(this.addressRequestPost.getPublicPlace())
                .isEqualTo("public place1");
    }

    @Test
    void getCity() {

        Assertions.assertThat(this.addressRequestPost.getCity())
                .isEqualTo("city1");
    }

    @Test
    void isMain() {

        Assertions.assertThat(this.addressRequestPost.isMain())
                .isTrue();
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.addressRequestPost)
                .isEqualTo(this.sameAddressRequestPost)
                .isNotEqualTo(this.differentAddressRequestPost);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.addressRequestPost)
                .hasSameHashCodeAs(this.sameAddressRequestPost);
    }
}