package com.harishkannarao.springsecurityrestapi.security.resolver;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AuthoritiesResolver {

    public List<GrantedAuthority> resolve(UserDetails user) {
        if ("user-name-1".equals(user.getUsername())) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return Collections.emptyList();
        }
    }
}
