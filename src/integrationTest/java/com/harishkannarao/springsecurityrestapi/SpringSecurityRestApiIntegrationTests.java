package com.harishkannarao.springsecurityrestapi;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpringSecurityRestApiIntegrationTests extends AbstractBaseDefaultProfileIntegrationTest {

	@Test
	void contextLoads() {
		assertThat(getTestApplicationUrl()).isNotBlank();
	}

}
