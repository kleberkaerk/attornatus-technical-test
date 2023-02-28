package com.attornatustechnicaltest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AttornatusTechnicalTestApplicationTests {

    @Test
    void contextLoads() {

        Assertions.assertThatCode(() -> AttornatusTechnicalTestApplication.main(new String[]{}))
                .doesNotThrowAnyException();
    }
}
