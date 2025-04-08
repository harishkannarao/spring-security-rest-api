package com.harishkannarao.springsecurityrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harishkannarao.springsecurityrestapi.domain.UserData;
import com.harishkannarao.springsecurityrestapi.security.helper.AuthenticationHelper;
import com.harishkannarao.springsecurityrestapi.security.resolver.UserDataResolver;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserDataBetaRestControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private final UserDataResolver userDataResolver = mock();
    private final AuthenticationHelper authenticationHelper = mock();
    private final UserDataBetaRestController userDataBetaRestController = new UserDataBetaRestController(
            userDataResolver,
            authenticationHelper);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userDataBetaRestController)
            .build();

    @Test
    public void getUserData_returns_success_response() throws Exception {
        String userName = "user-name" + UUID.randomUUID();
        String requestId = UUID.randomUUID().toString();
        when(authenticationHelper.getCurrentUsername(assertArg(request ->
                assertThat(request.getHeader("request-id")).isEqualTo(requestId)))
        ).thenReturn(userName);
        UserData expectedUserData = UserData.builder()
                .firstName("first-" + UUID.randomUUID())
                .lastName("last-" + UUID.randomUUID())
                .build();
        when(userDataResolver.resolve(userName))
                .thenReturn(Optional.of(expectedUserData));
        MvcResult result = mockMvc.perform(
                get("/beta/user-data")
                        .header("request-id", requestId)
        ).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        UserData userData = objectMapper.readValue(result.getResponse().getContentAsString(), UserData.class);

        assertThat(userData)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedUserData);
    }

    @Test
    public void getUserData_returns_bad_request_response() throws Exception {
        String userName = "user-name" + UUID.randomUUID();
        String requestId = UUID.randomUUID().toString();
        when(authenticationHelper.getCurrentUsername(assertArg(request ->
                assertThat(request.getHeader("request-id")).isEqualTo(requestId)))
        ).thenReturn(userName);
        when(userDataResolver.resolve(userName))
                .thenReturn(Optional.empty());
        MvcResult result = mockMvc.perform(
                get("/beta/user-data")
                        .header("request-id", requestId)
        ).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(400);
    }
}
