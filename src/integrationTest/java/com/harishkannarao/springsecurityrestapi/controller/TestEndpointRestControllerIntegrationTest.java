package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.AbstractBaseIntegrationTestProfile;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class TestEndpointRestControllerIntegrationTest extends AbstractBaseIntegrationTestProfile {

    @Test
    public void testEndpoints_returns204() {
        ResponseEntity<Void> result = testRestTemplate()
                .getForEntity("/test-endpoint", Void.class);

        assertThat(result.getStatusCode().value()).isEqualTo(204);
    }
}
