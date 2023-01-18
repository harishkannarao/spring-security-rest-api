package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.AbstractBaseIntegrationTestProfile;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class FallBackRestControllerIntegrationTest extends AbstractBaseIntegrationTestProfile {

    @Test
    public void test_nonExistentEndpoint_return401_forUnAuthenticatedRequest() {
        ResponseEntity<Void> result = testRestTemplate()
                .getForEntity("/non-existent-api/some-path", Void.class);
        assertThat(result.getStatusCode().value()).isEqualTo(401);
    }

    @Test
    public void test_nonExistentEndpoint_return403_forAuthenticatedRequest() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBearerAuth("user-token");
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Void> result = testRestTemplate()
                .exchange("/non-existent-api/some-path", HttpMethod.GET, requestEntity, Void.class);

        assertThat(result.getStatusCode().value()).isEqualTo(403);
    }
}
