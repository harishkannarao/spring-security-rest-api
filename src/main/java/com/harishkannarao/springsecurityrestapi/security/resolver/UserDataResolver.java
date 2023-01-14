package com.harishkannarao.springsecurityrestapi.security.resolver;

import com.harishkannarao.springsecurityrestapi.domain.UserData;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataResolver {

    public Optional<UserData> resolve(String username) {
        if ("user-name-1".equals(username)) {
            return Optional.of(UserData.builder()
                    .firstName("userFirstName")
                    .lastName("userLastName")
                    .build());
        } else {
            return Optional.empty();
        }
    }
}
