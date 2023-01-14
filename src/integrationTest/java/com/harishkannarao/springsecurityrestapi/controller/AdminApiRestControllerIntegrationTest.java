package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.AbstractBaseDefaultProfileIntegrationTest;
import com.harishkannarao.springsecurityrestapi.domain.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminApiRestControllerIntegrationTest extends AbstractBaseDefaultProfileIntegrationTest {

    @Test
    public void test_getUserData() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBearerAuth("admin-token");
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<UserData> result = testRestTemplate()
                .exchange("/admin/get-user-data/{username}", HttpMethod.GET, requestEntity, UserData.class, Map.of("username", "user-name-1"));

        assertThat(result.getStatusCode().value()).isEqualTo(200);
        UserData actualEntity = result.getBody();
        assertThat(actualEntity).isNotNull();
        assertThat(actualEntity.getFirstName()).isEqualTo("userFirstName");
        assertThat(actualEntity.getLastName()).isEqualTo("userLastName");
    }

    @Test
    public void test_getUserData_returns403_forNonAdminUser() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBearerAuth("user-token");
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Void> result = testRestTemplate()
                .exchange("/admin/get-user-data/{username}", HttpMethod.GET, requestEntity, Void.class, Map.of("username", "user-name-1"));

        assertThat(result.getStatusCode().value()).isEqualTo(403);
    }
}
