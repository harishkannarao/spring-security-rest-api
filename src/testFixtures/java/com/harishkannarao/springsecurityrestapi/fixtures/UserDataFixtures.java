package com.harishkannarao.springsecurityrestapi.fixtures;

import com.harishkannarao.springsecurityrestapi.domain.UserData;

public class UserDataFixtures {
    public static UserData anUserData() {
        return UserData.builder()
                .firstName("some-first-name")
                .lastName("some-last-name")
                .build();
    }
}
