package com.harishkannarao.springsecurityrestapi.security.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationResolver {

    private static final String BEARER_PREFIX = "Bearer ";

    private final UserDetailsResolver userDetailsResolver;
    private final AuthoritiesResolver authoritiesResolver;

    @Autowired
    public AuthenticationResolver(UserDetailsResolver userDetailsResolver, AuthoritiesResolver authoritiesResolver) {
        this.userDetailsResolver = userDetailsResolver;
        this.authoritiesResolver = authoritiesResolver;
    }

    public Optional<Authentication> resolve(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<String> token = extractToken(authHeader);
        Optional<UserDetails> userDetails = token.flatMap(userDetailsResolver::resolve);
        return userDetails.map(user -> new UsernamePasswordAuthenticationToken(user, null, authoritiesResolver.resolve(user)));
    }

    private Optional<String> extractToken(String authHeader) {
        if (authHeader.startsWith(BEARER_PREFIX)){
            return Optional.of(authHeader.substring(7));
        } else {
            return Optional.empty();
        }
    }
}
