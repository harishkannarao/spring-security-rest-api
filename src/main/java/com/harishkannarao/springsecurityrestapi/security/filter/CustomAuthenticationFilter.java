package com.harishkannarao.springsecurityrestapi.security.filter;

import com.harishkannarao.springsecurityrestapi.security.resolver.AuthenticationResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationResolver authenticationResolver;

    @Autowired
    public CustomAuthenticationFilter(AuthenticationResolver authenticationResolver) {
        this.authenticationResolver = authenticationResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<Authentication> resolvedAuthentication = authenticationResolver.resolve(request);
            resolvedAuthentication.ifPresent(authentication -> {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                request.setAttribute("authentication", authentication);
            });
        } catch (Exception e) {
            log.error("Error resolving authentication: " + e.getMessage(), e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}
