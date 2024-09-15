package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.AbstractBaseIntegrationTestProfile;
import com.harishkannarao.springsecurityrestapi.domain.GeneralData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralDataRestControllerIntegrationTest extends AbstractBaseIntegrationTestProfile {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public GeneralDataRestControllerIntegrationTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    public void test_getGeneralData() {
        ResponseEntity<GeneralData> result = testRestTemplate
                .getForEntity("/general-data", GeneralData.class);
        assertThat(result.getStatusCode().value()).isEqualTo(202);
        GeneralData actualEntity = result.getBody();
        assertThat(actualEntity).isNotNull();
        assertThat(actualEntity.getMessage()).isEqualTo("Welcome !!!");
    }
}
