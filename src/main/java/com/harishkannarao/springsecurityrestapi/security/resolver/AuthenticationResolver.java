package com.harishkannarao.springsecurityrestapi.security.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthenticationResolver {
    Optional<Authentication> resolve(HttpServletRequest request);
}
