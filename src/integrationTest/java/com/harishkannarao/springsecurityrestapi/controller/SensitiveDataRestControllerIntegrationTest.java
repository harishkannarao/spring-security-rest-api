package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.AbstractBaseDefaultProfileIntegrationTest;
import com.harishkannarao.springsecurityrestapi.domain.SensitiveData;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class SensitiveDataRestControllerIntegrationTest extends AbstractBaseDefaultProfileIntegrationTest {

    @Test
    public void test_getSensitiveData() {
        ResponseEntity<SensitiveData> result = testRestTemplate()
                .getForEntity("/sensitive-data", SensitiveData.class);
        assertThat(result.getStatusCode().value()).isEqualTo(200);
        SensitiveData actualEntity = result.getBody();
        assertThat(actualEntity).isNotNull();
        assertThat(actualEntity.getFirstName()).isEqualTo("secretFirstName");
        assertThat(actualEntity.getLastName()).isEqualTo("secretLastName");
    }
}
