package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.AbstractBaseIntegrationTestProfile;
import com.harishkannarao.springsecurityrestapi.domain.GeneralData;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralDataRestControllerIntegrationTest extends AbstractBaseIntegrationTestProfile {

    @Test
    public void test_getGeneralData() {
        ResponseEntity<GeneralData> result = testRestTemplate()
                .getForEntity("/general-data", GeneralData.class);
        assertThat(result.getStatusCode().value()).isEqualTo(200);
        GeneralData actualEntity = result.getBody();
        assertThat(actualEntity).isNotNull();
        assertThat(actualEntity.getMessage()).isEqualTo("Welcome !!!");
    }
}
