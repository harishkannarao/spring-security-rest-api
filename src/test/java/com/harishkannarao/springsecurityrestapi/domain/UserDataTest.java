package com.harishkannarao.springsecurityrestapi.domain;

import com.harishkannarao.springsecurityrestapi.fixtures.UserDataFixtures;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDataTest {

    @Test
    public void testEquals() {
        UserData userData1 = UserDataFixtures.anUserData().toBuilder()
                .lastName("something")
                .build();
        UserData userData2 = UserDataFixtures.anUserData().toBuilder()
                .lastName("something")
                .build();
        assertThat(userData1).isEqualTo(userData2);
    }
}
