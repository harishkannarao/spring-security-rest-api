package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.UserData;
import com.harishkannarao.springsecurityrestapi.security.helper.AuthenticationHelper;
import com.harishkannarao.springsecurityrestapi.security.resolver.UserDataResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = {"/user-data"})
public class UserDataRestController {

    private final UserDataResolver userDataResolver;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public UserDataRestController(UserDataResolver userDataResolver, AuthenticationHelper authenticationHelper) {
        this.userDataResolver = userDataResolver;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public ResponseEntity<UserData> getUserData() {
        Optional<UserData> userData = userDataResolver.resolve(authenticationHelper.getCurrentUsername());
        return userData.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
