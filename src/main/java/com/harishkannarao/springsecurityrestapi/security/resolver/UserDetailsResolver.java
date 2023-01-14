package com.harishkannarao.springsecurityrestapi.security.resolver;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailsResolver {

    public Optional<UserDetails> resolve(String token) {
        if ("user-token".equals(token)) {
            return Optional.of(
                    User.builder()
                            .username("user-name-1")
                            .password("")
                            .disabled(false)
                            .accountExpired(false)
                            .credentialsExpired(false)
                            .accountLocked(false)
                            .authorities(Collections.emptyList())
                            .build()
            );
        } else {
            return Optional.empty();
        }
    }
}
