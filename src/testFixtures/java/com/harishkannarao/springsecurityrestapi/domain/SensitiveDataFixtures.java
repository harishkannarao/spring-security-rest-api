package com.harishkannarao.springsecurityrestapi.domain;

public class SensitiveDataFixtures {
    public static UserData aSensitiveData() {
        return UserData.builder()
                .firstName("some-first-name")
                .lastName("some-last-name")
                .build();
    }
}
