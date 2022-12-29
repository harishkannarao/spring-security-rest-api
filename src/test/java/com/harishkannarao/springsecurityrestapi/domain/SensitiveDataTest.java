package com.harishkannarao.springsecurityrestapi.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SensitiveDataTest {

    @Test
    public void testEquals() {
        SensitiveData sensitiveData1 = SensitiveDataFixtures.aSensitiveData().toBuilder()
                .lastName("something")
                .build();
        SensitiveData sensitiveData2 = SensitiveDataFixtures.aSensitiveData().toBuilder()
                .lastName("something")
                .build();
        assertThat(sensitiveData1).isEqualTo(sensitiveData2);
    }
}
