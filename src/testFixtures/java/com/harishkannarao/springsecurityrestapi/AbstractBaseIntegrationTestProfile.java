package com.harishkannarao.springsecurityrestapi;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"integration-test"})
public abstract class AbstractBaseIntegrationTestProfile extends AbstractBaseIntegrationTest {
}
