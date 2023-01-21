package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.UserData;
import com.harishkannarao.springsecurityrestapi.fixtures.UserDataFixtures;
import com.harishkannarao.springsecurityrestapi.fixtures.UserDetailsFixtures;
import com.harishkannarao.springsecurityrestapi.security.helper.AuthenticationHelper;
import com.harishkannarao.springsecurityrestapi.security.resolver.UserDataResolver;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserDataRestControllerTest {

    private final UserDataResolver mockUserDataResolver = Mockito.mock(UserDataResolver.class);
    private final AuthenticationHelper mockAuthenticationHelper = Mockito.mock(AuthenticationHelper.class);
    private final UserDataRestController underTest = new UserDataRestController(mockUserDataResolver, mockAuthenticationHelper);

    @Test
    public void getUserData_returnsUserData() {
        UserDetails userDetails = UserDetailsFixtures.createAnUser();

        UserData userData = UserDataFixtures.anUserData();
        when(mockAuthenticationHelper.getCurrentUsername()).thenReturn(userDetails.getUsername());
        when(mockUserDataResolver.resolve(userDetails.getUsername())).thenReturn(Optional.of(userData));

        ResponseEntity<UserData> result = underTest.getUserData();
        assertThat(result.getStatusCode().value()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(userData);
    }

    @Test
    public void getUserData_returnsBadRequest_forNonExistentUserData() {
        UserDetails userDetails = UserDetailsFixtures.createAnUser();

        when(mockAuthenticationHelper.getCurrentUsername()).thenReturn(userDetails.getUsername());
        when(mockUserDataResolver.resolve(userDetails.getUsername())).thenReturn(Optional.empty());

        ResponseEntity<UserData> result = underTest.getUserData();
        assertThat(result.getStatusCode().value()).isEqualTo(400);
        assertThat(result.getBody()).isNull();
    }

}
