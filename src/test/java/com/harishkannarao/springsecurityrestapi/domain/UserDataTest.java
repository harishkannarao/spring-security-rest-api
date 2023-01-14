package com.harishkannarao.springsecurityrestapi.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDataTest {

    @Test
    public void testEquals() {
        UserData userData1 = SensitiveDataFixtures.aSensitiveData().toBuilder()
                .lastName("something")
                .build();
        UserData userData2 = SensitiveDataFixtures.aSensitiveData().toBuilder()
                .lastName("something")
                .build();
        assertThat(userData1).isEqualTo(userData2);
    }
}
