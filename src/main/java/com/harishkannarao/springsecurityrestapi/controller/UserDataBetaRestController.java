package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.UserData;
import com.harishkannarao.springsecurityrestapi.security.resolver.UserDataResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = {"/beta/user-data"})
@ConditionalOnProperty(name = "feature.beta.enabled", havingValue = "true")
public class UserDataBetaRestController {

    private final UserDataResolver userDataResolver;

    @Autowired
    public UserDataBetaRestController(UserDataResolver userDataResolver) {
        this.userDataResolver = userDataResolver;
    }

    @GetMapping
    public ResponseEntity<UserData> getUserData(@RequestAttribute("authentication") Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        Optional<UserData> userData = userDataResolver.resolve(principal.getUsername());
        return userData.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
