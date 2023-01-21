package com.harishkannarao.springsecurityrestapi.security.helper;

import com.harishkannarao.springsecurityrestapi.fixtures.AuthenticationFixtures;
import com.harishkannarao.springsecurityrestapi.fixtures.UserDetailsFixtures;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationHelperTest {

    private final AuthenticationHelper underTest = new AuthenticationHelper();

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.createEmptyContext();
    }

    @AfterEach
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void getCurrentUsername() {
        UserDetails anUser = UserDetailsFixtures.createAnUser();
        Authentication authentication = AuthenticationFixtures.createAuthentication(anUser);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String result = underTest.getCurrentUsername();

        assertThat(result).isEqualTo(anUser.getUsername());
    }

}