package com.harishkannarao.springsecurityrestapi.domain;

public class SensitiveDataFixtures {
    public static SensitiveData aSensitiveData() {
        return SensitiveData.builder()
                .firstName("some-first-name")
                .lastName("some-last-name")
                .build();
    }
}
