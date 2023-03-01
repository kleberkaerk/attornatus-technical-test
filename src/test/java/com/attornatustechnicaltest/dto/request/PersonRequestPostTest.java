package com.attornatustechnicaltest.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonRequestPostTest {

    private PersonRequestPost personRequestPost;
    private PersonRequestPost samePersonRequestPost;
    private PersonRequestPost differentPersonRequestPost;

    void setPersonRequestPost() {

        this.personRequestPost = PersonRequestPost.PersonRequestPostBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();
    }
    void setSamePersonRequestPost() {

        this.samePersonRequestPost = PersonRequestPost.PersonRequestPostBuilder.builder()
                .name("name1")
                .dateOfBirth("01-01-2000")
                .build();
    }
    void setDifferentPersonRequestPost() {

        this.differentPersonRequestPost = PersonRequestPost.PersonRequestPostBuilder.builder()
                .name("name2")
                .dateOfBirth("02-02-2002")
                .build();
    }

    @BeforeEach
    void initializeObjects() {

        this.setPersonRequestPost();
        this.setSamePersonRequestPost();
        this.setDifferentPersonRequestPost();
    }

    @Test
    void getName() {

        Assertions.assertThat(this.personRequestPost.getName())
                .isNotNull()
                .isEqualTo("name1");
    }

    @Test
    void getDateOfBirth() {

        Assertions.assertThat(this.personRequestPost.getDateOfBirth())
                .isNotNull()
                .isEqualTo("01-01-2000");
    }

    @Test
    void testEquals() {

        Assertions.assertThat(this.personRequestPost)
                .isEqualTo(this.samePersonRequestPost)
                .isNotEqualTo(this.differentPersonRequestPost);
    }

    @Test
    void testHashCode() {

        Assertions.assertThat(this.personRequestPost)
                .hasSameHashCodeAs(this.samePersonRequestPost);
    }
}